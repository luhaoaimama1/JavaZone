package Java.Zone.Utils;

import java.lang.reflect.Type;

public class TypeUtils {
	private static Class<?>[] wrapClasArr=new Class<?>[]{
			Integer.class,Double.class,Float.class,
			Long.class,Short.class,Boolean.class,
			Byte.class,Character.class,Void.class,
	};
	/**
	 * 是否是八大基本类型　　
	 * int, double, float, long, short, boolean, byte, char,void.
	 */
	public static boolean isPrimitive(Type type){
//		type.
		Class<?> clas = (Class<?>)type;
		return clas.isPrimitive();
	}
	/**
	 * 是否是八大基本类型的包装类
	 * @param type
	 * @return
	 */
	public static boolean isPrimitiveWrap(Type type){
		Class<?> clas = (Class<?>)type;
		for (Class<?> item : wrapClasArr) {
			if(clas.isAssignableFrom(item)){
				return true;
			}
		}
		return false;
	}
	public static  Class<?> toClass(Type type){
		Class<?> clas = (Class<?>)type;
		return clas;
	} 
}
