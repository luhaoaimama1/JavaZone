package 软硬引用的了解;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SoftTest2 {
	private static Reference<String> lin=null;
	public static void main(String[] args) {
		set(new String("gaga"));//！！！这里注意想要生效 必须是包装类 或者类 而不能是基础类型
		System.gc();
		System.out.println(lin.get());
	}
	public static void set(String str){
		lin=new SoftReference<String>(str);//有gaga
//		lin=new WeakReference<String>(str);//null
	}
}
