import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Ref {
	private enum PrintProperty{
		NO_TYPE,WITH_TYPE
	}
	private String a;
	private List<String> b;
	private int c;
	private static final String  DE="0";
//	private Ref d = new Ref();

	public Ref() {
		a = "a";
		b = new ArrayList<String>();
		b.add("abc");
		c = 1;
	}

	public static void main(String[] args)  {
		ref(new Ref(),PrintProperty.NO_TYPE);

	}
/**
 * aa
 * @param r
 * @param p
 */
	public static <T> void ref(T r,PrintProperty p)  {
		Field[] f = r.getClass().getDeclaredFields();
		for (Field field : f) {
			field.setAccessible(true);
			try {
				switch (p) {
				case NO_TYPE:
					System.out.println("字段名字："
							+ field.getName() + "\t字段的值:" + field.get(r));
					break;
				case WITH_TYPE:
					System.out.println("字段类型:" + field.getGenericType() + "\t字段名字："
							+ field.getName() + "\t字段的值:" + field.get(r));
					break;
				default:
					throw new UnsupportedOperationException("为实现此枚举的功能！");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
