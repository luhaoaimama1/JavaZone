package 注解学习;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Java.Zone.Log.PrintUtils;

public class Child {
	private String str;
	private Parent parent;
	private List<Parent> list = new ArrayList<Parent>();
//	private List<String> listStr = new ArrayList<String>();
	
	public static void main(String[] args) {
		Child c = new Child();
		Field[] fields = c.getClass().getDeclaredFields();
		for (Field field : fields) {
			FieldType temp = getFieldType(field);
			switch (temp.fieldProperty) {
			case CLASS:
			case LIST_CLASS:
//				try {
//					Class<?> d = Class.forName(temp.classStr);
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				break;

			default:
				break;
			}
		}
	}

	public static FieldType getFieldType(Field field) {
		FieldType fieldType=new FieldType();
		try {
			ParameterizedType type = (ParameterizedType) field.getGenericType();
			Type temp = type.getActualTypeArguments()[0];
//			System.out.println(field.getName() + " ___ParameterizedType___ \t "
//					+ type.getActualTypeArguments()[0]);
			fieldType.fieldProperty=FieldProperty.LIST_CLASS;
			fieldType.classStr=temp.toString();
			if(String.class.toString().equals(temp.toString())){
				fieldType.fieldProperty=FieldProperty.LIST_STRING;
			}
			// 好使
			 Class<?> lin=null;
			try {
				lin = Class.forName(type.getActualTypeArguments()[0].toString().split(" ")[1]);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
//			PrintUtils.printAllField(lin.);
			 System.out.println("啥玩意："+lin);
			// Parent a = (Parent) lin.newInstance();
		} catch (Exception e) {
			Type type = field.getGenericType();
			fieldType.classStr=type.toString();
			fieldType.fieldProperty=FieldProperty.CLASS;
//			System.out.println(field.getName() + "\t " + type);
			if(String.class.toString().equals(type.toString())){
				fieldType.fieldProperty=FieldProperty.STRING;
			}
		}
		return fieldType;
	}

	public static class FieldType {
		public String classStr;
		public FieldProperty fieldProperty;
		@Override
		public String toString() {
			PrintUtils.printAllField(this);
			return super.toString();
		}
	}
	public enum FieldProperty{
		STRING,CLASS,LIST_STRING,LIST_CLASS;
	}
}
