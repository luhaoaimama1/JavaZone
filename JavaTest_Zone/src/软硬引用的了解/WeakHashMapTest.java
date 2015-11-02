package 软硬引用的了解;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {
	public static void main(String[] args) {
		Map<Object, Object> objectMap = new WeakHashMap<Object, Object>();
		String a = String.valueOf(1);
		String b = String.valueOf(2);
		String c = String.valueOf(3);
		objectMap.put(a, new Object());
		objectMap.put(b, new Object());
		objectMap.put(c, new Object());
		System.gc();
		System.out.println("Map size :" + objectMap.size());
		a=null;
		b=null;
		c=null;
		System.gc();
		System.out.println("Map size :" + objectMap.size());
	}

}
