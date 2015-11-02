package entity;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class KbKb {
	public static void main(String[] args) {
		System.out.println(	String.class.toString());
		Field[] d = Te.class.getDeclaredFields();
		for (Field field : d) {
			field.setAccessible(true);
			if(typeIsNotEquals(String.class, field.getGenericType()))
				System.out.print("相等");
			else
				System.out.print("不相等");
			System.out.println(field.getGenericType());
		}
		
	}
	public static boolean typeIsNotEquals(Class classStr,Type type){
		if(classStr.toString().equals(type.toString())){
			return true;
		}
		return false;
	}
}
