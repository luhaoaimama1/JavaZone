package fu;

import java.lang.reflect.Field;
/**
 * @version 2015.7.15
 * @author Zone
 *
 */
public class PrintUtils {
	private static boolean isNotPrint=true; 
	public enum PrintProperty {
		NO_TYPE, WITH_TYPE
	}

	public static <T> void printAllField(T t) {
		printAllField(t, PrintProperty.NO_TYPE);
	}
	/**
	 * 
	 * @param t  ʵ��
	 * @param p	 PrintProperty	{NO_TYPE, WITH_TYPE}
	 */
	public static <T> void printAllField(T t, PrintProperty p) {
		if (isNotPrint) {
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
						throw new UnsupportedOperationException("Ϊʵ�ִ�ö�ٵĹ��ܣ�");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				i++;
			}
			sb.append("}");
			System.out.println(sb.toString());
		}
	}
	public static void openWriteLog(){
		isNotPrint=true;
	}
	public static void closeWriteLog(){
		isNotPrint=false;
	}
}
