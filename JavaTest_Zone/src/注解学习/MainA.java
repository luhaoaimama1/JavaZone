package 注解学习;

import java.lang.reflect.Field;

public class MainA {
	public static void main(String[] args) {
		test();
	}
	public static void test(){
		if(EntityA.class.getAnnotation(Table.class) != null){
			System.out.println(EntityA.class.getAnnotation(Table.class).name());
		}
		
		Field[] fields = EntityA.class.getDeclaredFields();
		for (Field field : fields) {
			if(field.getAnnotation(TestA.class)!=null){
				TestA an = field.getAnnotation(TestA.class);
				System.out.println("getAnnotation:"+field.getAnnotation(TestA.class));
				System.out.println(an.orderName()+"\t  ____"+an.newName());
			}
		}
	}
	
//	_name_ 中卫注解
//	Sqlite_Utils.getInstance(this).UpdateByCondition(DbEntity.class, " set _name_ =? " , new String[]{"pangci"});
	
//	UpdateByCondition(DbEntity.class, new String[]{"name"},"=",new String[]{"pangci"});
//	UpdateByCondition(DbEntity.class, new String[]{"name"}+" = "+new String[]{"pangci"});
//	UpdateByCondition(DbEntity.class, new String[]{"name"},"=",new String[]{"pangci"});
//	UpdateByCondition(DbEntity.class, new String[]{"name"},"=",new String[]{"pangci"});
}
