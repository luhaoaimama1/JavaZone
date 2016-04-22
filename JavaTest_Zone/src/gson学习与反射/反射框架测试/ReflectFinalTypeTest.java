package gson学习与反射.反射框架测试;

public class ReflectFinalTypeTest {
	private final String kb = "kb............";

	public static void main(String[] args) {
		ReflectFinalTypeTest f = Reflect.on(ReflectFinalTypeTest.class).create().set("kb", "mb").get();
		System.out.println(f.kb);
	}

}
