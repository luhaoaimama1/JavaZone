import java.io.File;

public class A {
	public static void main(String[] args) {
		// File a=new File("null");
		// if(("null").equals(a.getAbsolutePath()))
		// {
		// System.out.println("对啊");
		// }
		// System.out.println("a"+a.getAbsolutePath());
		A b = new A();
		aa(b);
	}
	public static <T> void aa(T b){
		System.out.println(b.getClass().getName());
	}
}
