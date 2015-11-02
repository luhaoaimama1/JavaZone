package 软硬引用的了解;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceQueueTest {
	private static Reference<String> lin = null;
	private static ReferenceQueue<String> q=new ReferenceQueue<String>();
	public static void main(String[] args) {
		set(new String("gaga"));// ！！！这里注意想要生效 必须是包装类 或者类 而不能是基础类型
		set(new String("bkbk"));
		System.gc();
		Reference<? extends String> aa=null;
		while((aa=q.poll())!=null){
			System.out.println("pip"+aa.get());
		}
	
		System.out.println("....");
	}

	public static void set(String str) {
		lin = new WeakReference(str, q);// 有gaga
	}
}
