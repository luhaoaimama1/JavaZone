package 软硬引用的了解;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class HashMapWeakKeyTest {
    public static void main(String[] args) throws InterruptedException {

        WeakReference<String> keyB = new WeakReference<String>(String.valueOf(2));
        WeakReference<String> keyC = new WeakReference<String>(String.valueOf(3));

        System.out.println("first keyC :" + keyC.get());
        System.out.println("first keyB :" + keyB.get());

        putMap(keyB, keyC);

        System.gc();
        Thread.sleep(500); //需要点延时 不没清除完毕
        System.out.println("keyC :" + keyC.get());
        System.out.println("keyB :" + keyB.get());
    }

    private static Map<Object, Object> putMap(WeakReference<String> keyB, WeakReference<String> keyC) {
        Map<Object, Object> objectMap = new HashMap<>();
        objectMap.put(1, keyB);
        objectMap.put(2, keyC);
        return objectMap;
    }
}
