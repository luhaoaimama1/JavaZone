package Java.Zone.Log;

import java.lang.reflect.Field;
/**
 * @version 2015.7.15
 * @author Zone
 *
 */
public class PrintUtils {
	private static boolean printLog=true; 
	public enum PrintProperty {
		NO_TYPE, WITH_TYPE
	}

	public static <T> void printAllField(T t) {
		printAllField(t, PrintProperty.NO_TYPE);
	}
	/**
	 * 
	 * @param t  实体
	 * @param p	 PrintProperty	{NO_TYPE, WITH_TYPE}
	 */
	public static <T> void printAllField(T t, PrintProperty p) {
		if (printLog) {
			Field[] f = t.getClass().getDeclaredFields();
			StringBuilder sb=new StringBuilder(50);
			sb.append("{");
			int i=0;
			for (Field field : f) {
				field.setAccessible(true);
				try {
					switch (p) {
					case NO_TYPE:
						if(i==0)
						sb.append("" + field.getName()+ ":" + field.get(t));	
						else
						sb.append("," + field.getName()+ ":" + field.get(t));
						break;
					case WITH_TYPE:
						if(i==0)
						sb.append("" + field.getGenericType()
									+ "_" + field.getName() + ":"
									+ field.get(t));
						else
						sb.append("," + field.getGenericType()
								+ "_" + field.getName() + ":"
								+ field.get(t));
						break;
					default:
						throw new UnsupportedOperationException("为实现此枚举的功能！");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				i++;
			}
			sb.append("}");
			print(sb.toString());
		}
	}
	public static void print(String message){
		if(printLog)
			System.out.println(message);
	}
	public static void openWritePrint(){
		printLog=true;
	}
	public static void closeWritePrint(){
		printLog=false;
	}
}
