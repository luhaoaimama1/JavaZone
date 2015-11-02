package Android.Zone.Sqlite;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * <br>功能介绍：
 * <br> 可以通过 实体类  发射生成数据库 1. byClassToGenerateCreateSqlString 2.create_table
 * <br>查询的结果会封装成两种： 1.queryToListAddMap 2.queryToEntity 你懂的
 * <br>getColumnNames: 可以得到字段名字
 * <br>column_updateOrDelete :更改表字段名和删除表字段  
 * <br>↑可以更改这两个字段：columns_new_create，import_columns 然后当参数传进去 也可以自己照这个格式去写然后传进去一样
 * <br>Operating 重写了两个方法：1.支持单条语句的增删改2.支持事务 多条语句的增删改
 * @author Zone
 *
 */
public class Sqlite_Dao extends SQLiteOpenHelper   {

	public static String DB_NAME = "mydata.db"; // 数据库名称
	// ------------------------第一个表--修改字段的时候这里需要不断想修改----------------------
	public static String TABLE_NAME = "xihuanbu"; // 表名
	// 创建表的语句
	public static String CreateSql = "CREATE TABLE  IF NOT EXISTS  "
			+ TABLE_NAME
			+ " (id INTEGER PRIMARY KEY AUTOINCREMENT ,name varchar(20),sex INTEGER);";
	//--------------**************版本更新的时候执行**************-------------------------------
	/**
	 * Sqlite_Dao更改表(删除字段或者修改字段)的情况  需要新的字段 
	 */
	public static String columns_new_create = "(id INTEGER PRIMARY KEY AUTOINCREMENT , name varchar(20));";
	/**
	 * 更改表的时候  旧表要导入新表的字段
	 * 例如：" id, "",sex " 可以用空格站位 因为可能删除了一个字段
	 */
	public static String import_columns = " id, name ";
	
	// ------------------------第二个表-------------------------
	// ------------------------第三个表-------------------------
	
	/**
	 * 
	 * @param context  你懂的
	 * @param Version  版本号  不用接口的那个
	 */
	public Sqlite_Dao(Context context,int Version) {
		super(context, DB_NAME, null, Version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 作用把这个类的生成表数据更改了  在通过调用此类的create_table 即可 创建表
	 *  <br> 这里有例子可以进来看   注释的部分为：通过 实体类 类创建表
	 * 
	 * @param t  反射需要的类 例如：DbEntity.class
	 * @return 得到表名  
	 */
	public static String byClassToGenerateCreateSqlString(Class t)
	{
//		//通过 实体类类创建表
//		String table=Sqlite_Dao.getClassProperty(DbEntity.class);
//		System.out.println("现在这个表是什么："+dao.CreateSql);
//		dao.create_table(dao.getWritableDatabase(), dao.CreateSql);
//		String[] cu=dao.getColumnNames(table);//得到 字段名字  验证表是否创建出来了
		
		StringBuffer sb=new StringBuffer(100);
		sb.append(" CREATE TABLE  IF NOT EXISTS  ");
		 String st = "CREATE TABLE  IF NOT EXISTS  "+ TABLE_NAME
				+ " (id INTEGER PRIMARY KEY AUTOINCREMENT ,name varchar(20),sex INTEGER);";
		Field[] fieds = t.getDeclaredFields();
		String[] str=t.getName().split("[.]");
		sb.append(str[str.length-1] +"(");
		System.out.println("类名字："+str[str.length-1] );
		int i=0;
		for (Field field : fieds) {
			if (i == 0) {
				if (field.getName().equals("id")) {
					sb.append("  id INTEGER PRIMARY KEY AUTOINCREMENT ");
				} else {
					sb.append(field.getName() + "  text(100)");
				}
			} else {
				if (field.getName().equals("id")) {
					sb.append(" , id INTEGER PRIMARY KEY AUTOINCREMENT ");
				} else {
					sb.append(" ," + field.getName() + "  text(100)");
				}
			}
			System.out.println("名字：" + field.getName());
			i++;
		}
		sb.append(");");
		System.out.println("创建表语句："+sb.toString());
		CreateSql=sb.toString();
		return str[str.length-1];
	}
	/**
	 * super(参数1，参数2，参数3，参数4)，其中参数4是代表数据库的版本， 是一个大于等于1的整数，如果要修改（添加字段）表中的字段，则设置
	 * 一个比当前的 参数4大的整数 ，把更新的语句写在onUpgrade(),下一次 调用
	 */
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 因为你运行一次后已经有schedule.db这个数据库文件了，所以你之后都不会调用onCreate这个方法了，你把FileExploer里的数据库文件删除就OK了。
		// --------------------------Create
		// table范例-----------------------------------------------------
		// String sql = "CREATE TABLE "+TABLE_NAME + "("
		// + "_id INTEGER PRIMARY KEY,"
		// + "name TEXT,"
		// + "sex);";
		try {
			db.execSQL(CreateSql);
			Log.e("Dao信息", TABLE_NAME + "======isExist:true");
		} catch (SQLException e) {
			Log.e("Dao报错信息","创建表失败  传入参数：CreateSql为" + String.valueOf(CreateSql));
			e.printStackTrace();
		} 
	}
	/**
	 *  外部用的创建表的方法 
	 * <br>顾可用于创建多表
	 * @param db2 你懂的 
	 * @param Create_Sql 额外创建的表语句
	 */
	public void create_table(SQLiteDatabase db2,String Create_Sql) {
		SQLiteDatabase db = db2;
		// --------------------------Create
		// table范例-----------------------------------------------------
		// String sql = "CREATE TABLE "+TABLE_NAME + "("
		// + "_id INTEGER PRIMARY KEY,"
		// + "name TEXT,"
		// + "sex);";
		try {
			db.execSQL(Create_Sql);
			Log.e("Dao信息", TABLE_NAME + "======isExist:true");
		} catch (SQLException e) {
			Log.e("Dao报错信息","创建表失败  传入参数：CreateSql为" + String.valueOf(CreateSql));
			e.printStackTrace();
		} finally {
				db.close();
		}
	}

	/**
	 * 修改版本的 时候 想修改数据在这里操作 更改就好了 可惜db有问题 弄不了 只能在外边调用方法更改了！！！！！
	 * <br>onUpgrade自带db也最好不关闭 放心
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 修改版本的 时候 想修改数据在这里操作 更改就好了 可惜db有问题 弄不了 只能在外边调用方法更改了！！！！！
		// db.execSQL("ALTER TABLE "+ MyHelper.TABLE_NAME+" ADD sex TEXT");
		// //修改字段
		try {
			// column_updateOrDelete(TABLE_NAME, columns_new_create,
			// import_columns);// 修改表中的字段 可复用
			Log.e("Dao信息", "更新表成功");
		} catch (SQLException e) {
			Log.e("Dao报错信息", "修改表失败  传入参数：UpdateSql为" + String.valueOf(""));
			e.printStackTrace();
		}finally{
//			db.close();
		}
	}

	/**
	 * <br>自带db关闭 放心
	 * @param sql
	 * @param object   不能传入null 可以　new　Object[]{};
	 * @return  返回成功与失败
	 */
	public boolean Operating(String sql, Object[] object) {
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
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			db.execSQL(sql, object);
			String[] split_str=sql.split("[?]");
			String Mosaic_str="";
			for (int i = 0; i < split_str.length; i++) {
				if(i!=split_str.length-1)
				{Mosaic_str+=split_str[i]+"\""+object[i]+"\"";}
				else{Mosaic_str+=split_str[i];}
			}
			Log.e("Dao成功信息", "Operating Success:"+Mosaic_str);
			return true;
		} catch (SQLException e) {
			Log.e("Dao报错信息", "Operating failed");
			return false;
		} finally {
			db.close();
		}

	}
	/**
	 * <br>自带db关闭 放心
	 * <br> 因为需要事务 所以重载的Operating 的方法 
	 * <br>拥有事务的功能
	 * @param sql
	 * @param objects    List<Object[]>;
	 * @return  返回成功与失败
	 */
	public boolean Operating(String sql, List<Object[]> objects) {
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
		SQLiteDatabase db = this.getWritableDatabase();
		//开启事务  
		db.beginTransaction();  
		try {
			for (Object[] object : objects) {
				db.execSQL(sql, object);
				String[] split_str=sql.split("[?]");
				String Mosaic_str="";
				for (int i = 0; i < split_str.length; i++) {
					if(i!=split_str.length-1)
					{Mosaic_str+=split_str[i]+"\""+object[i]+"\"";}
					else{Mosaic_str+=split_str[i];}
					Log.e("Dao成功信息", "Operating Success:"+Mosaic_str);
				}
			}
			//设置事务标志为成功，当结束事务时就会提交事务  
			db.setTransactionSuccessful();  
			return true;
		} catch (SQLException e) {
			Log.e("Dao报错信息", "Operating failed");
			return false;
		} finally {
			//结束事务  
			db.endTransaction(); 
			db.close();
		}
		
	}

	/**
	 * <br>自带db关闭 放心
	 * @param sql
	 * @param str  可以为 null  也可以new String[]{};
	 * @param log
	 *            是否在控制台打印 查询字段与数据
	 * @return   返回一个list套map<String,String>的数据
	 */
	public List<HashMap<String, String>> queryToListAddMap(String sql, String[] str,boolean log) {
		List<HashMap<String, String>> list_map = new ArrayList<HashMap<String, String>>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(sql, str);
			//此查询中得到的字段
			String[] lin2 = cursor.getColumnNames();
			Log.e("Dao信息", "字段有");
			for (int i = 0; i < lin2.length; i++) {
				Log.e("Dao信息", i + "L:" + lin2[i]);
			}
			Log.e("Dao信息", "结束");
			// Log.e("piu1", lin.toString());
			// Log.e("piu2", lin2[0].toString() + ":" + lin2[1].toString());
			if (cursor.getCount() == 0) {
				Log.e("Dao信息", "cursor 里面的数目为：0");
			} else {
				Log.e("Dao信息","cursor 里面的数目为:" + String.valueOf(cursor.getCount()));
				while (cursor.moveToNext()) {
					HashMap<String, String> map = new HashMap<String, String>();
					StringBuffer loge_temp = new StringBuffer();
					for (int i = 0; i < lin2.length; i++) {
						map.put(lin2[i], cursor.getString(cursor
								.getColumnIndex(lin2[i])));
						loge_temp.append(","
								+ lin2[i]
								+ ":"
								+ cursor.getString(cursor
										.getColumnIndex(lin2[i])));
					}
					if (log) {
						Log.e("Dao信息", "第" + cursor.getPosition() + "条数据====="
								+ loge_temp.toString().substring(1));
					}
					list_map.add(map);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("Dao报错信息", "查询游标发生异常");
			e.printStackTrace();
		} finally {
			db.close();
			cursor.close();
		}
		return list_map;
	}
	/**
	 * <br>自带db关闭 放心
	 * @param <T>  一个new好实体类
	 * @param sql  query 的sql语句
	 * @param str  可以为null 也可以new String[]{};
	 * @param log  是否在控制台打印 查询字段与数据
	 * @return  	List 的实体类
	 */
	public <T> List<T> queryToEntity(T t,String sql, String[] str,boolean log) {
		List<HashMap<String, String>> list_map = new ArrayList<HashMap<String, String>>();
		//最终要返回的实体数据
		List<T>  list_entity=new ArrayList<T>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(sql, str);
			//此查询中得到的字段
			String[] lin2 = cursor.getColumnNames();
			Log.e("Dao信息", "字段有");
			for (int i = 0; i < lin2.length; i++) {
				Log.e("Dao信息", i + "L:" + lin2[i]);
			}
			Log.e("Dao信息", "结束");
			
			Field[] fieds = t.getClass().getDeclaredFields();
			//得到实体中字段
			String[] entityColumn=new String[fieds.length];
			for (int i = 0; i < fieds.length; i++) {
				entityColumn[i]=fieds[i].getName();
				System.out.println("实体中字段："+entityColumn[i]);
			}
			
			// Log.e("piu1", lin.toString());
			// Log.e("piu2", lin2[0].toString() + ":" + lin2[1].toString());
			if (cursor.getCount() == 0) {
				Log.e("Dao信息", "cursor 里面的数目为：0");
			} else {
				Log.e("Dao信息","cursor 里面的数目为:" + String.valueOf(cursor.getCount()));
				//游标 循环遍历
				while (cursor.moveToNext()) {
					 //建立一个实体
					T lin_entity=(T) t.getClass().newInstance();
					//set之前  首先看数据库中得到字段 实体字段有没有  有的话就可以走set方法
					for (int i = 0; i < lin2.length; i++) {
						boolean columnNameIsExist = false;
						for (int j = 0; j < entityColumn.length; j++) {
							if (lin2[i].equals(entityColumn[j])) {
								columnNameIsExist = true;
							}
						}
						if (columnNameIsExist) {
//							 如果字段存在 开始往实体类 set
//							id   setId(String id)  效果这样
							String a = lin2[i];
							String b=a.substring(0, 1).toUpperCase();
							String c=a.substring(1);
							String finalStr="set"+b+c;
							//现在只支持String  
							Method method = t.getClass().getMethod(finalStr, String.class);
							//反射执行 set方法
							method.invoke(lin_entity, cursor.getString(cursor.getColumnIndex(lin2[i]))+"");
						}
					}
					//所有字段都set完毕  把实体 填进list中
					list_entity.add(lin_entity);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("Dao报错信息", "查询游标发生异常");
			e.printStackTrace();
		} finally {
			db.close();
			cursor.close();
		}
		return list_entity;
	}

	/**
	 * 更改字段 删除字段的时候用 （一般用在版本更新 发现db的bug 还是外边调用好了） 可以复用
	 * <br>自带db关闭 放心
	 * @param tableName
	 *            要更改字段的表名
	 * @param columns_new_create_Param
	 *            创建新表的语句的后半段
	 * @param import_columns_Param
	 *            原数据导入到新数据的字段
	 */
	public void column_updateOrDelete(String tableName,String columns_new_create_Param, String import_columns_Param) {
//		例子：dao.column_updateOrDelete(Sqlite_Dao.TABLE_NAME, Sqlite_Dao.columns_new_create,Sqlite_Dao.import_columns);		
		
		// Sqlite 不支持直接修改字段的名称。
		// 我们可以使用别的方法来实现修改字段名。
		// 1、修改原表的名称
		// ALTER TABLE table RENAME TO tableOld;
		// 2、新建修改字段后的表
		// CREATE TABLE table(ID INTEGER PRIMARY KEY AUTOINCREMENT,
		// Modify_Username text not null);
		// 3、从旧表中查询出数据 并插入新表
		// INSERT INTO table SELECT ID,Username FROM tableOld;
		// 4、删除旧表
		// DROP TABLE tableOld;
		
		
		//1、未命名旧表之前 怕存在  如果存在 先删除
		String sql_drop1 = "DROP TABLE IF EXISTS " + tableName + "_old";
		try {
			Operating(sql_drop1, new Object[] {});
		} catch (Exception e) {
			Log.e("Dao报错信息", "更改/删除字段---------删除此表的old表的表失败");
			//异常 失败之后就不继续了
			return;
		}
		//2、未命名旧表之前 怕存在  如果存在 先删除
		SQLiteDatabase db = this.getWritableDatabase();
		String rename = "ALTER TABLE " + tableName + "  RENAME TO " + tableName
				+ "_old";
		try {
			db.execSQL(rename);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Dao报错信息", "更改/删除字段---------把表命名成old系列失败");
			//异常 失败之后就不继续了
			db.close();
			return;
		}
		//3、新建修改字段后的表
		String table = "CREATE TABLE  IF NOT EXISTS  " + tableName
				+ columns_new_create_Param;
		try {
			db.execSQL(table);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Dao报错信息", "更改/删除字段---------创建新的空表失败");
			//异常 失败之后就不继续了
			db.close();
			return;
		}
		//4、从旧表中查询出数据 并插入新表
		String ex = "insert into " + tableName + " select "
				+ import_columns_Param + "  from " + tableName + "_old";
		try {
			Operating(ex, new Object[] {});
			Log.e("Dao信息", "原数据导入成功！！！");
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Dao报错信息", "更改/删除字段---------old表导入新表失败");
			//异常 失败之后就不继续了
			db.close();
			return;
			
		}
		// 5、删除旧表
		String sql_drop = "DROP TABLE IF EXISTS " + tableName + "_old";
		try {
			Operating(sql_drop, new Object[] {});
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Dao报错信息", "更改/删除字段---------删除old表失败");
			//异常 失败之后就不继续了
			db.close();
			return;
		}
	
		db.close();
		Log.e("Dao信息", "更改/删除字段---------成功");

	}

	/**
	 * 得到某个表的所有字段 如果没有字段会发生空指针异常
	 * <br>自带db关闭 放心 
	 * @param tableName
	 * @return  返回要查询的表中的字段
	 */
	public String[] getColumnNames(String tableName) {
		String[] lin2 = null;
		String sql = "select * from " + tableName;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(sql, null);
			lin2 = cursor.getColumnNames();
		} catch (Exception e) {
			Log.e("Dao报错信息", "表中无字段，不可用此方法");
			e.printStackTrace();
		} finally {
			db.close();
			cursor.close();
		}

		return lin2;
	}

}
