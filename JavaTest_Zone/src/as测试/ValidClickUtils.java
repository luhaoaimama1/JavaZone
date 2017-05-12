package as测试;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class ValidClickUtils {
    //记录上次的点击时间即可。通过object;
    private final static int SPACE_TIME = 5000;

    static Map<WeakReference<Object>, Long> map = new WeakHashMap();

    public synchronized static boolean isValid(Object obj) {
        WeakReference<Object> key = null;
        for (Map.Entry<WeakReference<Object>, Long> weakReferenceLongEntry : map.entrySet()) {
            if (weakReferenceLongEntry.getKey().get() == obj) {
                key = weakReferenceLongEntry.getKey();
                break;
            }
        }
        boolean isValid;
        long currentTime = System.currentTimeMillis();
        if (key != null) {
            //有值
            Long lastClick = map.get(key);
            if (currentTime - lastClick > SPACE_TIME) {
                isValid = true;
                map.put(key, currentTime);
            } else
                isValid = false;
        } else {
            //没值
            key = new WeakReference<Object>(obj);
            map.put(key, currentTime);
            isValid = true;
        }
        System.out.println("size:" + map.size());
        return isValid;
    }

    public synchronized static boolean isValid() {
        return isValid(ValidClickUtils.class);
    }

    public static void main(String[] args) throws InterruptedException {

        //  测试
        Pe obj = new Pe(1);
        System.out.println(ValidClickUtils.isValid(obj));
        Thread.sleep(3000);
        System.out.println(ValidClickUtils.isValid(obj));
        Thread.sleep(2000);

        //研究obj  map回收的问题   如果size 1成功

        obj = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("gc:");
        Pe obj2 = new Pe(2);
        System.out.println(ValidClickUtils.isValid(obj2));
        System.out.println("最后 size:" + map.size());
        for (Map.Entry<WeakReference<Object>, Long> weakReferenceLongEntry : map.entrySet()) {
            if (weakReferenceLongEntry.getKey().get() != null)
                System.out.println("含有的元素—index:" + ((Pe) weakReferenceLongEntry.getKey().get()).i);
        }
        System.out.println("最后 size:--->" + map.size());
    }

    public static class Pe {
        public int i;

        public Pe(int i) {
            this.i = i;
        }
    }
}