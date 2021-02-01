package 软硬引用的了解;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest2 {
    public static void main(String[] args) throws InterruptedException {
        Map<Activity, Pop> objectMap = new WeakHashMap<>();

        Activity key=new Activity();
        Pop value=new Pop(key);

        objectMap.put(key, value);
        System.gc();
        System.out.println("Map size :" + objectMap.size());
        key = null;
        System.gc();
        Thread.sleep(500); //需要点延时 不没清除完毕
        System.out.println("Map size :" + objectMap.size());
    }

    static class Activity {
        String value="acitivity";
    }
    static class Pop {
        WeakReference<Object> mShowingPopUp;
        public Pop(Activity key) {
            mShowingPopUp=new WeakReference(key);
        }
    }

}
