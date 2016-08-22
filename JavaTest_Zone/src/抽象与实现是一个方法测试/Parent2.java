package 抽象与实现是一个方法测试;

public abstract class Parent2{

	public Parent2() {
		System.out.println("Parent");
	}

	public   void gan(){
		System.out.println("干啥玩意 ");
	};

	public static class Child extends Parent2 implements Gan{


		@Override
		public void gan() {
			System.out.println("干你妹");
		}

		public void fei() {
			System.out.println("飞起来");
		}
	}
	interface Gan{
		void gan();
	}
	public static void main(String[] args) {
		//说明  一样的方法不会冲突
		new Parent2.Child().gan();
		((Gan)new Parent2.Child()).gan();
	}
}
