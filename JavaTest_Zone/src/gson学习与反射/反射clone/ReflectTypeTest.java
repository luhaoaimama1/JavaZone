package gson学习与反射.反射clone;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReflectTypeTest {
	private String str;
	private Parent parent;
	private List<Parent> list = new ArrayList<Parent>();
	private Parent[] arr = new Parent[]{};
	public static void main(String[] args) {
		ReflectTypeTest c = new ReflectTypeTest();
		Field[] fields = c.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				ParameterizedType type = (ParameterizedType) field
						.getGenericType();
				System.out.println(field.getName() + "\t "+ type.getActualTypeArguments()[0]);
			} catch (Exception e) {
				Type type = field.getGenericType();
				System.out.println(" Exception:\t"+field.getName() + "\t " + type);
			}
		}
	}
}