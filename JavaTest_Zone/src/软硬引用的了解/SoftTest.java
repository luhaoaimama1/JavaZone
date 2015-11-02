package 软硬引用的了解;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class SoftTest {
	public static void main(String[] args) {
		Map<Integer,Reference<String>> map=new HashMap<Integer,Reference<String>>(10000000);
		for (int i = 0; i < 10000; i++) {
			System.out.println(i);
			String lin=""+i;
			Reference<String> a=new SoftReference<String>(lin);
			lin=null;
			map.put(i, a);
		}
		System.gc();
		System.out.println("tian:"+map.get(0).get());
	}

}
