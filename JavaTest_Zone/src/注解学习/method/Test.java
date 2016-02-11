package 注解学习.method;

import java.lang.reflect.InvocationTargetException;

public class Test {
	public static void main(String[] args) {
		try {
			System.out.println(ParseAnnotationStub.parseMethod(HelloWorldStub.class));
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException | SecurityException
				| NoSuchMethodException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
