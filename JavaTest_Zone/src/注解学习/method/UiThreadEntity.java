package 注解学习.method;

public class UiThreadEntity {
	@UiThread
	void test(){
		System.out.println("test");
	}

}
