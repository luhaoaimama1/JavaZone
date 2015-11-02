package Android.Zone.Sqlite;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import Android.Zone.Sqlite.Annotation.utils.AnUtils;
import Android.Zone.Sqlite.GsonEntity.GsonColumn;
import Android.Zone.Sqlite.Inner.TempUtils;
import Android.Zone.Sqlite.Inner.UpdateColumn;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class Sqlite_Helper  extends SQLiteOpenHelper  {
	private static final String TAG="Sqlite_Helper";
	private static final String SQL_ERR="sql执行失败 执行的sql为";
	
	private boolean noTran=true;

	
	public boolean isTran() {
		return noTran;
	}

	public void setTran(boolean tran) {
		this.noTran = tran;
	}

	public Sqlite_Helper(String DbNname, Context context, int version) {
		super(context,DbNname, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	/**
	 * 因为  他都是在别的方法中调用所以  db不关闭
	 * @param t
	 * @param db
	 * @return
	 */
	public <T> boolean existTable(Class<T> t,SQLiteDatabase db){
		boolean result=false;
		String sql="SELECT COUNT(*) FROM sqlite_master where type='table' and name='"+	AnUtils.getTableAnnoName(t)+"'";
		try {
			 Cursor cursor = db.rawQuery(sql, null);
             if(cursor.moveToNext()){
                     int count = cursor.getInt(0);
                     if(count>0){
                             result = true;
                     }
             }
		} catch (SQLException e) {
			Log.e(TAG+"报错信息",SQL_ERR + sql);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 *  外部用的创建表的方法 
	 * <br>顾可用于创建多表
	 * @param <T>
	 * @param <T>
	 * @param db2 你懂的 
	 * @param Create_Sql 额外创建的表语句
	 */
	public  <T> boolean createTableByEntity(Class<T> t,SQLiteDatabase db) {
		if(existTable(t, db)){//提高效率
			//如果表存在即不用管了
			if (noTran) {
				//如果没有事物 就关闭
				db.close();
			}
			return false;
		}
		String Create_Sql=byClassToGenerateCreateSqlString(t);
		// --------------------------Create
		// table范例-----------------------------------------------------
		// String sql = "CREATE TABLE "+TABLE_NAME + "("
		// + "_id INTEGER PRIMARY KEY,"
		// + "name TEXT,"
		// + "sex);";
		boolean success=false;
		try {
			db.execSQL(Create_Sql);
			success=true;
		} catch (SQLException e) {
			Log.e(TAG+"报错信息",SQL_ERR + Create_Sql);
			e.printStackTrace();
			return success;
		} finally {
				if (noTran) {
					db.close();
				}
		}
		return success;
	}
	public enum AddColumnStatue{
		Success,Fail,Exist;
	}
	public <T> AddColumnStatue addColumn(Class<T> table,String willAddColumnStr,String length,SQLiteDatabase db){
//		 db.execSQL("ALTER TABLE "+ MyHelper.TABLE_NAME+" ADD sex TEXT");
		String[] columns = getColumnNames(table,db,false);
		
		for (String item : columns) {
			if(willAddColumnStr.equals(item))
				return AddColumnStatue.Exist;
		}
		boolean success=false;
		String sql="ALTER TABLE "+ 	AnUtils.getTableAnnoName(table)+" ADD "+willAddColumnStr+" TEXT("+length+") ";
		try {
			db.execSQL(sql);
			success=true;
		} catch (SQLException e) {
			Log.e(TAG+"报错信息",SQL_ERR+ sql);
			e.printStackTrace();
			success=false;
		} finally {
			System.out.println("db shi openma ?"+db.isOpen());
				if (noTran&&db.isOpen()) {
					db.close();
				}
		}
		if(success){
			return AddColumnStatue.Success;
		}else{
			return AddColumnStatue.Fail;
		}
	}
	/**
	 * 可以走 删除字段  但是 不用既可  不走删除字段  直走 length修改
	 * 即这里不解析  最原始的 
	 *            要更改字段的表名
	 *            创建新表的语句的后半段
	 * columns_new_create = "(id INTEGER PRIMARY KEY AUTOINCREMENT , name varchar(20));";
	 *            原数据导入到新数据的字段
	 *   import_columns = " id, name ";
	 * @param t
	 * @param updateColumnList
	 * @param db
	 */
	public <T> boolean column_updateOrDelete(Class<T> t,List<GsonColumn> noUpdateList, List<UpdateColumn> updateColumnList  ,SQLiteDatabase db ) {
//		例子：dao.column_updateOrDelete(Sqlite_Dao.TABLE_NAME, Sqlite_Dao.columns_new_create,Sqlite_Dao.import_columns);		
		
		// Sqlite 不支持直接修改字段的名称。
		// 我们可以使用别的方法来实现修改字段名。
		// 1、修改原表的名称
		// ALTER TABLE table RENAME TO tableOld;
		// 2、新建修改字段后的表
		// CREATE TABLE table(ID INTEGER PRIMARY KEY AUTOINCREMENT,
		// Modify_Username text not null);
		// 3、从旧表中查询出数据 并插入新表
		// INSERT INTO table(id,username) SELECT ID,Username FROM tableOld;
		// 4、删除旧表
		// DROP TABLE tableOld;
		StringBuffer sb_noUpdateList=new StringBuffer();
		for (GsonColumn item : noUpdateList) {
			sb_noUpdateList.append(item.getName()+",");
		}
		String str_noUpdateList = sb_noUpdateList.toString();
		str_noUpdateList=str_noUpdateList.substring(0,str_noUpdateList.length()-1);
		
		List<String> columnNames=new ArrayList<String>();
		List<String> columnNames_Target=new  ArrayList<String>();
		for (UpdateColumn item : updateColumnList) {
			//当目标为""空字符串的时候 那么就是删除此字段
			if(!("").equals(item.column_target.trim())){
				columnNames.add(item.column_old);
				columnNames_Target.add(item.column_target);
			}
		}
		
		String tableName=AnUtils.getTableAnnoName(t);
		//1、未命名旧表之前 怕存在  如果存在 先删除
		String sql_drop1 = "DROP TABLE IF EXISTS " + tableName + "_old";
		try {
			Operating(sql_drop1, new Object[] {},db,false);
		} catch (Exception e) {
			Log.e(TAG+"报错信息",SQL_ERR+ sql_drop1+"______________删除此表的old表的表失败");
			e.printStackTrace();
			return false;
		}
		//2、未命名旧表之前 怕存在  如果存在 先删除
		String rename = "ALTER TABLE " + tableName + "  RENAME TO " + tableName
				+ "_old";
		try {
			db.execSQL(rename);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG+"报错信息",SQL_ERR+ rename+"______________把表命名成old系列失败");
			if (noTran) {
				db.close();
			}
			return false;
		}
	
		//3、新建修改字段后的表  即现在的类创建表
//		String table=byClassToGenerateCreateSqlString(t);
//		String st = "CREATE TABLE  IF NOT EXISTS  "+ TABLE_NAME
//		+ " (id INTEGER PRIMARY KEY AUTOINCREMENT ,name varchar(20),sex INTEGER);";
		StringBuffer sb_table=new StringBuffer();
		sb_table.append("CREATE TABLE  IF NOT EXISTS "+tableName+" (id INTEGER PRIMARY KEY AUTOINCREMENT ");
//		willAddColumnStr+" TEXT("+length+") "
		for (GsonColumn gsonItem : noUpdateList) {
			if(!("id").equals(gsonItem.getName())&&!("").equals(gsonItem.getName().trim())){
				//不等与ID就处理
				sb_table.append(","+gsonItem.getName()+" TEXT("+gsonItem.getLength()+")  ");
			}
		}
		for (UpdateColumn item : updateColumnList) {
			if(!("").equals(item.column_target.trim())){
				sb_table.append(","+item.column_target+" TEXT("+item.targetLength+")  ");
			}
		}
		sb_table.append(");");
		String table=sb_table.toString();
		try {
			db.execSQL(table);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG+"报错信息",SQL_ERR+ table+"______________创建新的空表失败");
			if (noTran) {
				db.close();
			}
			return false;
		}
		//当字段是""  即查询的是老子段对应的 字段去除
		//当字段不是空  即原来的字段即可
//		(id,username) SELECT ID,Username 

		boolean insertSucess=false;
		//4、从旧表中查询出数据 并插入新表
		String import_columns_Param="";
		String import_columns_Param_new="";
		if(("").equals(listMergeSymbol(columnNames, ","))){
			import_columns_Param=str_noUpdateList;
			import_columns_Param_new=str_noUpdateList;
		}else{
			import_columns_Param=str_noUpdateList+","+listMergeSymbol(columnNames, ",");
			import_columns_Param_new=str_noUpdateList+","+listMergeSymbol(columnNames_Target, ",");
		}
		
		String ex = "insert into " + tableName + " ("+import_columns_Param_new+") select "
				+ import_columns_Param + "  from " + tableName + "_old";
		try {
			insertSucess=Operating(ex, new Object[] {},db,false);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG+"报错信息",SQL_ERR+ ex+"______________old表导入新表失败");
			if (noTran) {
				db.close();
			}
			return false;
			
		}
		// 5、删除旧表
		String sql_drop = "DROP TABLE IF EXISTS " + tableName + "_old";
		try {
			Operating(sql_drop, new Object[] {},db,false);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG+"报错信息",SQL_ERR+ sql_drop+"______________删除old表失败");
			if (noTran) {
				db.close();
			}
			return false;
		}
	
		if (noTran) {
			db.close();
		}
	
		if (Sqlite_Utils.getPrintLog()) {
			if (insertSucess) {
				Log.d(TAG,
						"表：" + t.getSimpleName() + "\t "+ listMergeSymbol(columnNames, ",") + "---->>>"
								+ listMergeSymbol(columnNames_Target, ",")+ "更改/删除字段---------成功");
			}
		}
		return true;

	}
	private String listMergeSymbol(List<String> noUpdateList,String symbol){
		StringBuffer temp=new StringBuffer();
		for (String string : noUpdateList) {
			temp.append(string+symbol);
		}
		String str_noUpdateList=temp.toString();
		if(str_noUpdateList.length()>=1){
			str_noUpdateList=str_noUpdateList.substring(0,temp.toString().length()-1);
		}
		return str_noUpdateList;
	}
	/**
	 * 作用把这个类的生成表数据更改了  在通过调用此类的create_table 即可 创建表
	 *  <br> 这里有例子可以进来看   注释的部分为：通过 实体类 类创建表
	 * @param <T>
	 * @param <T>
	 * 
	 * @param t  反射需要的类 例如：DbEntity.class
	 * @return 得到表名  
	 */
	private  <T> String byClassToGenerateCreateSqlString(Class<T> t)
	{
		
//		String st = "CREATE TABLE  IF NOT EXISTS  "+ TABLE_NAME
//				+ " (id INTEGER PRIMARY KEY AUTOINCREMENT ,name varchar(20),sex INTEGER);";
		StringBuffer sb=new StringBuffer(100);
		sb.append(" CREATE TABLE  IF NOT EXISTS  ");
		Field[] fieds = t.getDeclaredFields();
		sb.append(AnUtils.getTableAnnoName(t) +"(");
		int i=0;
		for (Field field : fieds) {
			if (i == 0) {
				if (field.getName().equals("id")) {
					sb.append("  id INTEGER PRIMARY KEY AUTOINCREMENT ");
				} else {
					/** 判断类型是  String */
					if(TempUtils.typeIsEquals(String.class, field.getGenericType())){
						sb.append(AnUtils.getAnnoColumn_Name_ByField(field, t) + "  text("+AnUtils.getAnnoColumn_Length_ByField(field, t)+")");
					}
				}
			} else {
				if (field.getName().equals("id")) {
					sb.append(" , id INTEGER PRIMARY KEY AUTOINCREMENT ");
				} else {
					if(TempUtils.typeIsEquals(String.class, field.getGenericType())){
						sb.append(" ," + AnUtils.getAnnoColumn_Name_ByField(field, t) + "  text("+AnUtils.getAnnoColumn_Length_ByField(field, t)+")");
					}
				}
			}
			i++;
		}
		sb.append(");");
		if (Sqlite_Utils.getPrintLog()) {
			Log.d(TAG, "创建表语句：" + sb.toString());
		}
		return sb.toString();
	}
	/**
	 * 得到某个表的所有字段 如果没有字段会发生空指针异常
	 * <br>自带db关闭 放心 
	 * @param <T>
	 * @param tableName
	 * @return  返回要查询的表中的字段
	 */
	public <T> String[] getColumnNames(Class<T> t,SQLiteDatabase db ,boolean closeDb) {
		String tableName=AnUtils.getTableAnnoName(t);
		String[] lin2 = null;
		String sql = "select * from " + tableName;
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(sql, null);
			lin2 = cursor.getColumnNames();
		} catch (Exception e) {
			Log.e(TAG+"报错信息","表中无字段，不可用此方法");
			e.printStackTrace();
		} finally {
			if (noTran&&closeDb) {
				db.close();
			}
			if(cursor!=null){
				cursor.close();
			}
		}

		return lin2;
	}
	public <T> String[] getColumnNames(Class<T> t,SQLiteDatabase db ) {
		return getColumnNames(t, db,true);
	}
	public int queryCountById(String sql, String[] str,boolean log) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = null;
		cursor = db.rawQuery(sql, str);
		return cursor.getCount();
	}
	
	/**
	 * <br>自带db关闭 放心
	 * @param <T>  一个new好实体类
	 * @param sql  query 的sql语句
	 * @param str  可以为null 也可以new String[]{};
	 * @param log  是否在控制台打印 查询字段与数据
	 * @return  	List 的实体类
	 */
	public <T> List<T> queryToEntity(Class<T> t,String sql, String[] str,boolean log,SQLiteDatabase db ) {
		// ----------------------------------修改范例-------------------------------------------------
				// SQLiteDatabase db = helper.getWritableDatabase();
				// String sql = "update " + MyParameters.TABLE_NAME
				// + " set name = ?,sex=?  where _id = ?;";
				// db.execSQL(sql, new Object[] { person.getName(), person.getSex(), id
				// });
				// db.close();
		//最终要返回的实体数据
		List<T>  list_entity=new ArrayList<T>();
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(sql, str);
			//此查询中得到的字段
			String[] columnNames = cursor.getColumnNames();
			Field[] fieds = t.getDeclaredFields();
			//得到实体中字段
			List<Field> fieldList=new ArrayList<Field>();
			for (int i = 0; i < fieds.length; i++) {
				if(TempUtils.typeIsEquals(String.class, fieds[i].getGenericType())){
					fieldList.add(fieds[i]);
				}
					
			}
			if (cursor.getCount() == 0) {
			} else {
				//游标 循环遍历
				while (cursor.moveToNext()) {
					 //建立一个实体
					T lin_entity=(T) t.newInstance();
					//set之前  首先看数据库中得到字段 实体字段有没有  有的话就可以走set方法
					for (int i = 0; i < columnNames.length; i++) {
						boolean columnNameIsExist = false;
						int fieldIndex=-1;
						for (Field item : fieldList) {
							if (columnNames[i].equals(AnUtils.getAnnoColumn_Name_ByField(item, t))) {
								fieldIndex=fieldList.indexOf(item);
								columnNameIsExist = true;
							}
						}
						if (columnNameIsExist) {
//							 如果字段存在 开始往实体类 set
//							id   setId(String id)  效果这样
							String a = fieldList.get(fieldIndex).getName();
							String b=a.substring(0, 1).toUpperCase();
							String c=a.substring(1);
							String finalStr="set"+b+c;
							//现在只支持String  
							Method method = t.getMethod(finalStr, String.class);
							//反射执行 set方法
							method.invoke(lin_entity, cursor.getString(cursor.getColumnIndex(columnNames[i]))+"");
						}
					}
					//所有字段都set完毕  把实体 填进list中
					list_entity.add(lin_entity);
				}
			}
		} catch (Exception e) {
			Log.e(TAG+"报错信息", "查询游标发生异常  或者数据库表不存在");
			e.printStackTrace();
		} finally {
			if (cursor!=null) {
				cursor.close();
			}
			if (noTran) {
				db.close();
			}
		}
		return list_entity;
	}
	
	public boolean Operating(String sql, Object[] object,SQLiteDatabase db) {
		return Operating(sql, object, db,true);
	}
	/**
	 * <br>自带db关闭 放心
	 * @param sql
	 * @param object   不能传入null 可以　new　Object[]{};
	 * @return  返回成功与失败
	 */
	public boolean Operating(String sql, Object[] object,SQLiteDatabase db,boolean dbClose) {
		// ----------------------------------修改范例-------------------------------------------------
		// SQLiteDatabase db = helper.getWritableDatabase();
		// String sql = "update " + MyParameters.TABLE_NAME
		// + " set name = ?,sex=?  where _id = ?;";
		// db.execSQL(sql, new Object[] { person.getName(), person.getSex(), id
		// });
		// db.close();
		// ----------------------------------删除范例-------------------------------------------------
		// SQLiteDatabase db = helper.getWritableDatabase();
		// String sql = "delete  from " + MyParameters.TABLE_NAME
		// + " where  _id=? ";
		// db.execSQL(sql, new Object[] { id });
		// db.close();
		// ----------------------------------插入范例-------------------------------------------------
		// String
		// sql="insert into "+MyParameters.TABLE_NAME+"(name, sex) values(?,?)";
		// db.execSQL(sql, new Object[]{person.getName(),person.getSex()});
		// db.execSQL(sql, object);
		// db.close();
		// ----------------------------------删除表范例-------------------------------------------------
		//delete from table 输出表中全部数据
		// 使用db.execSQL("delete from table",null);总是报错?  
		// 原因是db.execSQL("delete from table",null);第二个参数不能为null，传递new
		// Object[]{}就OK了。
		// db.execSQL("DROP TABLE IF EXISTS students"); //这个好用
		try {
			db.execSQL(sql, object);
			return true;
		} catch (SQLException e) {
			Log.e(TAG+"报错信息",SQL_ERR + sql);
			return false;
		} finally {
			if (Sqlite_Utils.getPrintLog()) {
				System.out.println("是否关闭了数据库："+ (noTran == true ? "是" : "否"));
			}
			if (noTran&&dbClose) {
				db.close();
			}
		}
	}
}
