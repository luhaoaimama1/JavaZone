package 软硬引用的了解;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakTest {
	public static void main(String[] args) {
		String abc = new String("abc");
		abc = new String("abcd");
		ReferenceQueue<String> queue = new ReferenceQueue<String>();
		WeakReference<String> abcWeakRef = new WeakReference<String>(abc, queue);
		Map<String, WeakReference<String>> map = new WeakHashMap<String,WeakReference<String>>();
		map.put("a", abcWeakRef);
		abc = null;
		System.out.println("before gc: " + abcWeakRef.get());
		System.out.println("before gc: " + map.get("a"));
		System.gc();
		System.out.println("after gc: " + abcWeakRef.get());
		System.out.println("before gc: " + map.get("a"));

	}

}
