package 软硬引用的了解;

import java.lang.ref.WeakReference;

public class Test {
	public static WeakReference<String> link;

	public static void main(String[] args) {
		test();
		System.out.println(link.get());
		System.gc();
		System.out.println(link.get());
	}

	public static void test() {
//		String s = "abc";
		String s = new String("abc");
		link = new WeakReference<String>(s);
	}

}