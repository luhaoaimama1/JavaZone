package Thread与Executor的学习;

public class ThreadLocalStudy {
	//不同线程存取　不会相互影响　而且用到的是一个变量！！！
	private  static ThreadLocal<Boolean> mTreadLocal_boolean=new ThreadLocal<Boolean>();
	private  static ThreadLocal<Integer> mTreadLocal_int=new ThreadLocal<Integer>();
	public static void main(String[] args) {
		mTreadLocal_boolean.set(true);
		mTreadLocal_int.set(100);
		new Thread("thread2"){
			public void run() {
				mTreadLocal_boolean.set(false);
				mTreadLocal_int.set(2);
				System.out.println("thread2:"+mTreadLocal_boolean.get()+"\t"+mTreadLocal_int.get());
			};
		}.start();
		
		new Thread("thread3"){
			public void run() {
				System.out.println("thread3:"+mTreadLocal_boolean.get()+"\t"+mTreadLocal_int.get());
			};
		}.start();
		try {
			Thread.sleep(1000);
			System.out.println("mainThread:"+mTreadLocal_boolean.get()+"\t"+mTreadLocal_int.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
//　out:
//	thread2:false	2
//	thread3:null	null
//	mainThread:true	100
}
