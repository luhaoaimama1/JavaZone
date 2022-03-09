package ky;


import java.util.HashMap;
import java.util.concurrent.*;

/**
 * callback 放在onReVisibility的原因就是为了可以让不同页面完成同一个任务
 */
public class TimeRewardManagerV2 {

    public static final String TAG = "TimeRewardManagerV2";
    private static volatile TimeRewardManagerV2 singleton;

    private TimeRewardManagerV2() {
    }

    public static TimeRewardManagerV2 getInstance() {
        if (singleton == null) {
            synchronized (TimeRewardManagerV2.class) {
                if (singleton == null) {
                    singleton = new TimeRewardManagerV2();
                }
            }
        }
        return singleton;
    }

    private static class Entity<T> {
        long leftTime;
        long startTime = -1L;
        long stayRewardMillTime;
        T obj;
        Runnable runnable;
        Runnable runnableReal;
        String key;

        public Entity(String key, long stayRewardMillTime, T obj, IRewardSuccess<T> iRewardSuccess) {
            this.key = key;
            this.leftTime = stayRewardMillTime;
            this.stayRewardMillTime = stayRewardMillTime;
            this.obj = obj;
            this.runnable = () -> {
                TimeRewardManagerV2.getInstance().map.remove(key);
                iRewardSuccess.onRewardSuccess(obj);
            };
        }
    }

    private final HashMap<String, Entity> map = new HashMap<>();

    public <T> void addTimeTask(String key, long stayRewardMillTime, T object, IRewardSuccess<T> iRewardSuccess) {
        System.out.println("click：" + key);
        map.put(key, new Entity(key, stayRewardMillTime, object, iRewardSuccess));
    }

    public synchronized void onVisibleToUserChanged(String key, boolean visibility) {
        Entity entity = map.get(key);
        if (entity == null) {
            System.out.println("key:" + key + "未找到任务!");
            return;
        }

        if (visibility) {
            if (entity.startTime != -1) { //可见到可见
                System.out.println("key:" + key + "已经可见 所以不需要处理");
                return;
            }
            //不可见到可见
            entity.startTime = System.currentTimeMillis();
            entity.runnableReal = (Runnable) getExecutors().schedule(entity.runnable, entity.leftTime, TimeUnit.MILLISECONDS);
        } else {
            if (entity.startTime == -1) { //不可见到不可见
                System.out.println("key:" + key + "未先可见就触发不可见Runnable");
                return;
            }
            //可见到不可见
            long costTime = System.currentTimeMillis() - entity.startTime;
            boolean remove = getExecutors().remove(entity.runnableReal);
            System.out.println("key:" + key + "移除任务：" + remove);
            entity.leftTime = entity.leftTime - costTime;
            entity.startTime = -1;
        }
    }

    private ScheduledThreadPoolExecutor getExecutors() {
        return (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
    }

    public interface IRewardSuccess<T> {
        void onRewardSuccess(T obj);
    }

    public static void main(String[] args) throws InterruptedException {
        TimeRewardManagerV2.getInstance().addTimeTask("1", 3000, "task1", new IRewardSuccess<String>() {
            @Override
            public void onRewardSuccess(String obj) {

                System.out.println("onRewardSuccess:" + obj);
            }
        });

        TimeRewardManagerV2.getInstance().addTimeTask("2", 3000, "task2", new IRewardSuccess<String>() {
            @Override
            public void onRewardSuccess(String obj) {
                System.out.println("onRewardSuccess:" + obj);
            }
        });


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                testRepeat();

//                testManyTask();

//                inteceptTask();

                timeNoOver();
            }

            private void inteceptTask() {
                System.out.println(1);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", true);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(2);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", false);


                System.out.println(3);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", true);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(4);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", false);

                System.out.println(5);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", true);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(6);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", false);
            }

            private void testManyTask() {
                System.out.println(1);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", true);
                try {
                    Thread.sleep(4000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(2);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", false);


                System.out.println(3);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("2", true);
                try {
                    Thread.sleep(4000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(4);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("2", false);
            }

            private void testRepeat() {
                System.out.println(1);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", true);
                try {
                    Thread.sleep(4000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(2);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", false);


                System.out.println(3);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", true);
                try {
                    Thread.sleep(4000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(4);
                TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", false);
            }
        });
        thread.start();
//        thread.join();
        Thread.sleep(1000 * 1000);
//        System.out.println("over!");
    }

    private static void timeNoOver() {
        System.out.println(1);
        TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", true);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(2);
        TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", false);


        System.out.println(3);
        TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", true);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(4);
        TimeRewardManagerV2.getInstance().onVisibleToUserChanged("1", false);
    }
}