package 软硬引用的了解;

import java.lang.ref.PhantomReference;   
import java.lang.ref.Reference;   
import java.lang.ref.ReferenceQueue;   
import java.lang.reflect.Field;   

public class PhantomTest {
	public static boolean isRun = true;

	public static void main(String[] args) throws Exception {
		String abc = new String("abc");
		System.out.println(abc.getClass() + "@" + abc.hashCode());
		final ReferenceQueue referenceQueue = new ReferenceQueue<String>();
//		new Thread() {
//			public void run() {
//				while (isRun) {
//					Object o = referenceQueue.poll();
//					if (o != null) {
//						try {
//							Field rereferent = Reference.class.getDeclaredField("referent");
//							rereferent.setAccessible(true);
//							Object result = rereferent.get(o);
//							System.out.println("gc will collect:"+ result.getClass() + "@"+ result.hashCode()+" value:"+result);
//							
//						} catch (Exception e) {
//
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		}.start();
		PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc,
				referenceQueue);
		abc = null;
		System.gc();
		Thread.currentThread().sleep(3000);
		Reference<? extends String> aa=null;
		while((aa=referenceQueue.poll())!=null){
			System.out.println("pip："+(aa==abcWeakRef));
		}
		isRun = false;
	}

}
