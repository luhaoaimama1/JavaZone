package 软硬引用的了解;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) throws InterruptedException {
        Map<Object, Object> objectMap = new WeakHashMap<Object, Object>();
        String a = String.valueOf(1);
        String b = String.valueOf(2);
        String c = String.valueOf(3);
        objectMap.put(a, "a");
        objectMap.put(b, "b");
        objectMap.put(c, "c");
        System.gc();
        System.out.println("Map size :" + objectMap.size());
        a = null;
        b = null;
        c = null;
        System.gc();
        Thread.sleep(500); //需要点延时 不没清除完毕
        System.out.println("Map size :" + objectMap.size());
        System.out.println(objectMap.get(String.valueOf(1)));
    }
}
