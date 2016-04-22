package 抽象与实现是一个方法测试;

public abstract class Parent {

	public   void gan(){
		System.out.println("干啥玩意 ");
	};
	
	public static class Child extends Parent implements Gan{

		@Override
		public void gan() {
			System.out.println("干你妹");
		}
	}
	interface Gan{
		void gan();
	}
	public static void main(String[] args) {
		//说明  一样的方法不会冲突
		new Parent.Child().gan();
		((Gan)new Parent.Child()).gan();
	}
}
