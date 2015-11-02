package Android.Zone.Sqlite;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;
import Android.Zone.Sqlite.Sqlite_Helper.AddColumnStatue;
import Android.Zone.Sqlite.Annotation.utils.AnUtils;
import Android.Zone.Sqlite.GsonEntity.GsonColumn;
import Android.Zone.Sqlite.GsonEntity.GsonTop;
import Android.Zone.Sqlite.Inner.OperateStatueEntity;
import Android.Zone.Sqlite.Inner.OperateStatueEntity.OperateStatue;
import Android.Zone.Sqlite.Inner.UpdateColumn;
import Android.Zone.Sqlite.TableEntity.TableEntity;
import Android.Zone.Utils.SharedUtils;
import Java.Zone.Log.PrintUtils;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//TODO 实体类id字段的设计   实体类中包含实体类的操作
/**
 * TableEntity.class  是数据库  维护表信息的表 每次自动更改和添加字段 都是重这里对比的
 * 如果你一些字段不小心删除 并且忘记了 可以在这里查到  如果想真正删除数据库中的
 * 字段必须用特殊方法
 *   
 * 必须有id  暂时支持String
 * 实体中包含实体 未实现  
 * 通过字段更改更改表未实现  
 * @author Zone
 */
public class Sqlite_Utils {
	private static String DB_NAME = "Zone.db"; // 数据库名称
	//因为一个 应用就一个数据库 所以版本号  静态就OK了
	private  static  int version=1;//默认第一个版本是1  
	private  static final  String SHARE_TAG="zone_db_version";
	private Sqlite_Helper helper;
	private static Sqlite_Utils instance=null;
	private static OnUpgrade onUpgrade=null;
	private static OnCreate onCreate=null;
	private static boolean printLog=false;
	private SQLiteDatabase tranSQLiteDatabase=null;
	/**
	 * 
	 * @param context 只是不同的 SQLiteOpenHelper 但是一个应用就是一个数据库
	 */
	private  Sqlite_Utils(Context context) {
		helper=new Sqlite_Helper(DB_NAME, context,1);
	}
	/**
	 * 只要注册监听   就会 创建表  和监听版本切换 
	 * @param context
	 * @param onCreate
	 * @param onUpgrade
	 */
	public static void  init_listener(Context context,OnCreate onCreate,OnUpgrade onUpgrade){
		Sqlite_Utils.onUpgrade=onUpgrade;
		Sqlite_Utils.onCreate=onCreate;
		Sqlite_Utils.getInstance(context);
		if(instance!=null){
			instance.createTableByEntity(TableEntity.class);
			onCreate.onCreateTable(instance);	
			onUpgrade(context,instance);
		}
	}
	/**======================== update======================
	 * @param instance2  别改成instance  因为私有变量有instance了。。。
	 */
	private static  void onUpgrade(Context context, Sqlite_Utils instance2){
		int oldVersion=SharedUtils.getInstance(context).readSp().getInt(SHARE_TAG, 0);
		SharedUtils.getInstance(context).writeSp().putInt(SHARE_TAG, version).commit();
		if(oldVersion!=version&&oldVersion!=0){
			//自动检测 添加字段 或者 修改长度  后才能注解字段删除和修改
			onUpgrade.onUpgrade(oldVersion, version,instance2);
			onUpgrade.annoColumn_DeleOrUpdate(instance2);
		}
	}
	public  interface OnUpgrade{
		/**
		 * 这个 仅仅是 自动检测 主要是监控类 的添加字段 和长度修改
		 * @param oldVersion
		 * @param newVersion
		 * @param instance2
		 */
		public  void onUpgrade(int oldVersion, int newVersion, Sqlite_Utils instance2);
		/**
		 * 主要是  对注解的修改 和 删除 
		 * @param instance2
		 */
		public  void annoColumn_DeleOrUpdate(Sqlite_Utils instance2);
	}
	public  interface OnCreate{
		/**
		 * 自动建表：不监控表内的变化 仅仅没有创建表 
		 * @param instance
		 */
		public  void onCreateTable(Sqlite_Utils instance);
	}
	public void workWithTran(Work work) {
		try {
			if(work==null)
				throw new IllegalArgumentException("work may be null");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		tranSQLiteDatabase = helper.getWritableDatabase();
		helper.setTran(false);
		tranSQLiteDatabase.beginTransaction();
		try {
			work.work();
			tranSQLiteDatabase.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 结束事务
			tranSQLiteDatabase.endTransaction();
			tranSQLiteDatabase.close();
			helper.setTran(true);
			tranSQLiteDatabase=null;
		}
	}
	public interface Work{
		public abstract void work();
	}
	/**
	 * 经证明 context 公用一个数据库  Sqlite_Utils 也是一个因为我用的是单例模式
	 * @param activity
	 * @return
	 */
	public static Sqlite_Utils getInstance(Context context){
		if(onUpgrade==null||onCreate==null)
			throw new IllegalStateException(" Must first init method(init_addOnUpgrade_listener) ");
		if(instance==null){
			instance=new Sqlite_Utils(context);
		}
		return instance;
	}
	private SQLiteDatabase getTranDatabase(){
		if (tranSQLiteDatabase != null) {
			return tranSQLiteDatabase;
		} else {
			return helper.getWritableDatabase();
		}
	}
	
	/**
	 * @param t
	 * @return
	 */
	public  <T> String[] queryColumnNamesByEntity(Class<T> t){
		String[] columns=helper.getColumnNames(t,getTranDatabase());
		if (printLog) {
			StringBuilder sb = new StringBuilder(100);
			sb.append("TableName:" + AnUtils.getTableAnnoName(t) + "\t{");
			int i = 0;
			for (String string : columns) {
				if (i == 0) {
					sb.append(" \t" + string);
				} else {
					sb.append(", \t" + string);
				}
				i++;
			}
			sb.append("\t}");
			System.out.println(sb.toString());
		}
		return columns;
	}
	/**
	 * @param t
	 * @param whereStr
	 * @param markStrArr
	 * @return
	 */
	public <T> List<T> queryEntitysByCondition(Class<T> t,String whereStr,String[] markStrArr){
		String sql="select * from "+ AnUtils.getTableAnnoName(t) +" "+AnUtils.replaceByAnnoColumn(whereStr, t);
		List<T> list = helper.queryToEntity(t, sql, markStrArr, true,getTranDatabase());
		if (printLog) {
			System.out.println("sql:"+sql);
			for (T t2 : list) {
				PrintUtils.printAllField(t2);
			}
		}
		return list;
	}
	/**
	 * @param t
	 * @param id
	 * @return
	 */
	public <T> T queryEntityById(Class<T> t,String id){
		String sql="select * from "+AnUtils.getTableAnnoName(t)+" where id=?";
		List<T> list = helper.queryToEntity(t, sql, new String[]{id}, true,getTranDatabase());
		if(list.size()>1){
			try {
				throw new IllegalStateException("id have same is not possible!");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		if (list.size() == 1) {
			if (printLog) {
				System.out.println("sql:"+sql);
				PrintUtils.printAllField(list.get(0));
			}
			return list.get(0);
		} else
			return null;
		
	}
	/**
	 * @param t
	 * @return
	 */
	public <T> List<T> queryAllByClass(Class<T> t){
		String sql="select * from "+AnUtils.getTableAnnoName(t);
		List<T> list = helper.queryToEntity(t, sql, new String[]{}, true,getTranDatabase());
		if (printLog) {
			System.out.println("sql:"+sql);
			for (T t2 : list) {
				PrintUtils.printAllField(t2);
			}
		}
		return list;
	}
	/**
	 * @param t
	 */
	public <T> void addOrUpdateEntity(T t){
		String id=null;
		try {
			Field idField = t.getClass().getDeclaredField("id");
			idField.setAccessible(true);
			id=(String) idField.get(t);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		if(id==null){
			//添加操作
			addEntity(t);
		}else{
			//看看ID重复没 
			String sql="select * from "+AnUtils.getTableAnnoName(t.getClass())+" where id=?";
			if (printLog) {
				System.out.println("sql:" + sql);
			}
			int count = helper.queryCountById(sql, new String[]{id}, true);
			if(count==0){
				//不重复就添加
				addEntity(t);
			}else{
				//重复就修改
				UpdateEntity(t);
			}
		}
	
	}
	/**
	 * @param t
	 */
	public <T> void addEntity(T t){
		// String
		// sql="insert into "+MyParameters.TABLE_NAME+"(name, sex) values(?,?)";
		// db.execSQL(sql, new Object[]{person.getName(),person.getSex()});
		// db.execSQL(sql, object);
		// db.close();
		StringBuilder sb=new StringBuilder(50);
		sb.append("insert into ");
		sb.append(AnUtils.getTableAnnoName(t.getClass())+" ");
		Field[] fields = t.getClass().getDeclaredFields();
		StringBuilder columnSb=new StringBuilder(50);
		StringBuilder markSb=new StringBuilder(50);
		columnSb.append("(");
		markSb.append("(");
		int i=0;
		List<Object> columeValue=new ArrayList<Object>();
		//TODO 当字段有问题 是一个类呢  未作
		for (Field field : fields) {
			if(!"id".equals(field.getName())){
				field.setAccessible(true);
				if (i==0) {
					columnSb.append(AnUtils.getAnnoColumn_Name_ByField(field, t.getClass()));
					markSb.append("?");
				}else{
					columnSb.append(","+AnUtils.getAnnoColumn_Name_ByField(field, t.getClass()));
					markSb.append(",?");
				}
				i++;
				try {
					columeValue.add((String) field.get(t));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		columnSb.append(")");
		markSb.append(")");
		sb.append(columnSb.toString()+" values "+markSb.toString());
		String sql=sb.toString();
		if (printLog) {
			System.out.println("sql:" + sql);
		}
		Object[] object=columeValue.toArray();
		helper.Operating(sql, object,getTranDatabase());
	}
	/**
	 * @param t
	 */
	public <T> void UpdateEntity(T t){
		// SQLiteDatabase db = helper.getWritableDatabase();
		// String sql = "update " + MyParameters.TABLE_NAME
		// + " set name = ?,sex=?  where _id = ?;";
		// db.execSQL(sql, new Object[] { person.getName(), person.getSex(), id
		// });
		// db.close();
		StringBuilder sb=new StringBuilder(50);
		sb.append("update ");
		sb.append(AnUtils.getTableAnnoName(t.getClass())+" set ");
		Field[] fields = t.getClass().getDeclaredFields();
		int i=0;
		List<Object> columeValue=new ArrayList<Object>();
		//TODO 当字段有问题 是一个类呢  未作
		for (Field field : fields) {
			if(!"id".equals(field.getName())){
				field.setAccessible(true);
				if (i==0) 
					sb.append(AnUtils.getAnnoColumn_Name_ByField(field, t.getClass())+"=?");
				else
					sb.append(","+AnUtils.getAnnoColumn_Name_ByField(field, t.getClass())+"=?");
				i++;
				try {
					columeValue.add((String) field.get(t));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		sb.append(" where id=? ");
		try {
			Field idField = t.getClass().getDeclaredField("id");
			idField.setAccessible(true);
			columeValue.add(idField.get(t));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		String sql=sb.toString();
		if (printLog) {
			System.out.println("sql:" + sql);
		}
		Object[] object=columeValue.toArray();
		helper.Operating(sql, object,getTranDatabase());
	}
	/**
	 *  " set name = ?,sex=?  where _id = ?;";
	 * db.execSQL(sql, new Object[] { person.getName(), person.getSex(), id});
	 * @param t
	 * @param Set_Where_Str
	 * @param markStrArr
	 */
	public <T> void UpdateByCondition(Class<T> t,String Set_Where_Str,String[] markStrArr){
		// SQLiteDatabase db = helper.getWritableDatabase();
		// String sql = "update " + MyParameters.TABLE_NAME
		// + " set name = ?,sex=?  where _id = ?;";
		// db.execSQL(sql, new Object[] { person.getName(), person.getSex(), id});
		// db.close();
		String sql="update   "+AnUtils.getTableAnnoName(t)+" "+AnUtils.replaceByAnnoColumn(Set_Where_Str, t);
		if (printLog) {
			System.out.println("sql:" + sql);
		}
		helper.Operating(sql,markStrArr,getTranDatabase());
	}
	public <T> void removeEntity(T t){
//		 SQLiteDatabase db = helper.getWritableDatabase();
		// String sql = "delete  from " + MyParameters.TABLE_NAME
		// + " where  _id=? ";
		// db.execSQL(sql, new Object[] { id });
		// db.close();	
		Object id = null;
		try {
			Field idField = t.getClass().getDeclaredField("id");
			idField.setAccessible(true);
			id=idField.get(t);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		try {
			if(id==null)
				throw new IllegalArgumentException("id may be null");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.err.println("id:"+id);
		String whereStr=" where id=? ";
		String sql="delete  from "+AnUtils.getTableAnnoName(t.getClass())+" "+whereStr;
		if (printLog) {
			System.out.println("sql:" + sql);
		}
		Object[] markStrArr=new Object[]{id};
		helper.Operating(sql, markStrArr,getTranDatabase());
	}

	public <T> void removeEntityByCondition(Class<T> t,String whereStr,String[] markStrArr){
		String sql="delete  from "+AnUtils.getTableAnnoName(t)+" "+	AnUtils.replaceByAnnoColumn(whereStr, t);
		if (printLog) {
			System.out.println("sql:" + sql);
		}
		helper.Operating(sql, markStrArr,getTranDatabase());
	}
	public <T> void removeAllByClass(Class<T> t){
		String sql="delete  from "+AnUtils.getTableAnnoName(t)+" ";
		if (printLog) {
			System.out.println("sql:" + sql);
		}
		helper.Operating(sql,new Object[]{},getTranDatabase());
	}

	public <T> void dropTableByClass(Class<T> t){
		// ----------------------------------删除表范例-------------------------------------------------
				//delete from table 输出表中全部数据
				// 使用db.execSQL("delete from table",null);总是报错?  
				// 原因是db.execSQL("delete from table",null);第二个参数不能为null，传递new
				// Object[]{}就OK了。
				// db.execSQL("DROP TABLE IF EXISTS students"); //这个好用
		String sql="DROP TABLE IF EXISTS "+AnUtils.getTableAnnoName(t)+" ";
		if (printLog) {
			System.out.println("sql:" + sql);
		}
		boolean success = helper.Operating(sql,new Object[]{},getTranDatabase());
		if(success){
			removeEntityByCondition(TableEntity.class, "where _tableName_ = ?", 
					new String[]{AnUtils.getTableAnnoName(t)});	
		}

	}
	public  <T> void createTableByEntity(Class<T> t){
		boolean suceess = helper.createTableByEntity(t, getTranDatabase());
		if (printLog) {
			System.out.println("表：" + AnUtils.getTableAnnoName(t) + "\t　创建陈功了吗"+ suceess);
		}
		if (suceess) {
			if(!t.getName().equals(TableEntity.class.getName())){
				//创建表成功
				TableEntity entity=new TableEntity();
				entity.setTableName(AnUtils.getTableAnnoName(t));
				//gson 存值的
				entity.setColumnProperty(AnUtils.getGsonStr(t));
				//记录下来
				addEntity(entity);
			}
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  <T> void createTableByList(List<Class> classList){
		for (Class class1 : classList) {
			createTableByEntity(class1);
		}
	}
	public <T> void addColumn(Class<T> t,String willAddColumnStr,String length){
		AddColumnStatue temp = helper.addColumn(t, willAddColumnStr,length,getTranDatabase());
		if(temp==AddColumnStatue.Success){
			//1.取出来  
			List<TableEntity> tempEntity= (List<TableEntity>) queryEntitysByCondition(TableEntity.class, "where _tableName_ = ?", 
						new String[]{AnUtils.getTableAnnoName(t)});
			//2.添加一个
			if(tempEntity.size()>0){
				TableEntity  finalTable=tempEntity.get(0);
				if(finalTable!=null){
					Gson gson=new Gson();
					GsonTop gsonTop = gson.fromJson(tempEntity.get(0).getColumnProperty(), GsonTop.class);
					gsonTop.getData().add(new GsonColumn(willAddColumnStr,length));
					String gsonTopStr = gson.toJson(gsonTop);
					finalTable.setColumnProperty(gsonTopStr);
				}
				//3.然后修改了
				UpdateEntity(finalTable);
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> void updateLengthOrAddColumn_AutoByList(List<Class> classList) {
		for (Class class1 : classList) {
			updateLengthOrAddColumn_Auto(class1);
		}
	}
	public <T> void updateLengthOrAddColumn_Auto(Class<T> t) {
		//1.取出来  
		List<TableEntity> tempEntity= (List<TableEntity>) queryEntitysByCondition(TableEntity.class, "where _tableName_ = ?", 
					new String[]{AnUtils.getTableAnnoName(t)});
		//2.添加一个
		if(tempEntity.size()>0){
			TableEntity  finalTable=tempEntity.get(0);
			Gson gson=new Gson();
			GsonTop gsonTop = gson.fromJson(tempEntity.get(0).getColumnProperty(), GsonTop.class);
			//数据库中存的 字段
			List<GsonColumn> dbList = gsonTop.getData();
			List<GsonColumn> noUpdateList =new ArrayList<GsonColumn>();
			for (GsonColumn item : dbList) {
				noUpdateList.add(item);
			}
			//当前类的 所有字段
			List<GsonColumn> nowList=new ArrayList<GsonColumn>();
			Field[] fields = t.getDeclaredFields();
			for (Field item : fields) {
				nowList.add(new GsonColumn(AnUtils.getAnnoColumn_Name_ByField(item, t), 
						AnUtils.getAnnoColumn_Length_ByField(item, t)));
			}
			List<OperateStatueEntity> operateList=new ArrayList<OperateStatueEntity>();
			for (GsonColumn nowItem : nowList) {
				boolean have=false;
				for (GsonColumn dbItem : dbList) {
					if(nowItem.getName().equals(dbItem.getName())){
						//如果有  
						have=true;	
						if(!nowItem.getLength().equals(dbItem.getLength())){
							//如果长度不想等 那么修改  如果相等那么不管
							operateList.add(new OperateStatueEntity(OperateStatue.Length, nowItem.getName(), nowItem.getLength()));
//							  即 数据库中的字段-变化的  +add的
							noUpdateList.remove(dbItem);
						}
					}
				}
				if(!have){
					//数据库总没有 那么添加
					operateList.add(new OperateStatueEntity(OperateStatue.Add, nowItem.getName(), nowItem.getLength()));
//					  即 数据库中的字段-变化的  +add的
					noUpdateList.add(nowItem);
				}
			}
//			3.把所有将要操作的 操作了
			List<UpdateColumn> updateColumnList=new ArrayList<UpdateColumn>();
			boolean ZoneTable_Add=false;
			for (OperateStatueEntity item : operateList) {
				switch (item.statue) {
				case Add:
					//方法里面自己带 添加内部表ZoneTable 字段了
					addColumn(t, item.name, item.length);
					ZoneTable_Add=true;
					break;
				case Length:
					//因为我只是修改长度  所以两个是相同的
					updateColumnList.add(new UpdateColumn(item.name, item.name, item.length));
					break;

				default:
					break;
				}
			}
			if(updateColumnList.size()>0){
				//修改是一起修改了 需要不变的字段  即 数据库中的字段-变化的  +add的
				boolean success = helper.column_updateOrDelete(t,noUpdateList, updateColumnList, getTranDatabase());
				if(success){
					//ZoneTable 字段更改了  为什么在查一边呢 因为 那个 add字段的时候 已经把数据更改了  所以要加判断提高效率
					if(ZoneTable_Add){
						tempEntity= (List<TableEntity>) queryEntitysByCondition(TableEntity.class, "where _tableName_ = ?", 
								new String[]{AnUtils.getTableAnnoName(t)});
						if(tempEntity.size()>0){
							finalTable=tempEntity.get(0);
							//这个就是最新的
							gsonTop = gson.fromJson(tempEntity.get(0).getColumnProperty(), GsonTop.class);
						}
					}
//					给gsonTop 实体 弄成修改后的实体
					for (UpdateColumn updateColumn : updateColumnList) {
						for (GsonColumn topItem : gsonTop.getData()) {
							if(topItem.getName().equals(updateColumn.column_old)){
								topItem.setLength(updateColumn.targetLength);
							}
						}
					}
					String gsonTopStr = gson.toJson(gsonTop);
					finalTable.setColumnProperty(gsonTopStr);
					//3.然后修改了
					UpdateEntity(finalTable);
				}
			}
		}
	}
	public <T> void deleteAnnoColumn(Class<T> t,String annoName){
		updateAnnoColumn(t, annoName, "");
	}
	public <T> void updateAnnoColumn(Class<T> t,String annoName,String newName){
		if(("").equals(annoName.trim())){
			throw new IllegalArgumentException("annoName may be ''!");
		}
		
		List<TableEntity> tempEntity = (List<TableEntity>) queryEntitysByCondition(TableEntity.class, "where _tableName_ = ?", 
				new String[]{AnUtils.getTableAnnoName(t)});
		Gson gson=new Gson();
		if(tempEntity.size()>0){
			TableEntity finalTable = tempEntity.get(0);
			//这个就是最新的
			GsonTop gsonTop = gson.fromJson(tempEntity.get(0).getColumnProperty(), GsonTop.class);
			//
			List<GsonColumn> noUpdateList=new ArrayList<GsonColumn>();
			for (GsonColumn gsonItem : gsonTop.getData()) {
				noUpdateList.add(gsonItem);
			}
			for (GsonColumn tableEntity : gsonTop.getData()) {
				if(annoName.equals(tableEntity.getName())){
					//找到了   即修改表结构
					noUpdateList.remove(tableEntity);
					List<UpdateColumn> updateColumnList=new ArrayList<UpdateColumn>();
					updateColumnList.add(new UpdateColumn(annoName, newName, tableEntity.getLength()));
					
					boolean success=helper.column_updateOrDelete(t,noUpdateList, updateColumnList, getTranDatabase());
					if(success){
						//如果成功了  那么把内部表 修改了 
						if(!("").equals(newName.trim())){
							//如果 为空的话 内部表 json 字符串 也没有此字段
							noUpdateList.add(new GsonColumn(newName, tableEntity.getLength()));
						}
						gsonTop.setData(noUpdateList);
						//转成json 串  存起来
						finalTable.setColumnProperty(gson.toJson(gsonTop));
						//然后修改了
						UpdateEntity(finalTable);
					}
					break;
				}
			}
		}
		
	}
	public static boolean getPrintLog(){
		return printLog;
	}
	public static void setPrintLog(boolean printLog){
		Sqlite_Utils.printLog=printLog;
	}
	public static int getVersion() {
		return version;
	}
	public static void setVersion(int version) {
		Sqlite_Utils.version = version;
	}
}
