package 并发.valatitle;

/**
 * Created by fuzhipeng on 2016/12/2.
 */
public class VolatileTest {
    //TODO  volatile 第一个语义 所有线程的可见性。保证读取的值是最新;
    //TODO  volatile 第2个语义 禁止指令重排序优化;


    //TODO 正确性的: 1. 确保单一线程(多线程就要 同步)修改值, 2 读取的话,是没有问题的;
    static volatile boolean shutdownRequested;

    public void shutdown() {
        shutdownRequested = true;
    }

    public void doWork() {
        while (!shutdownRequested) {
            System.out.println(Thread.currentThread() + ":haha");
        }
        System.out.println(Thread.currentThread() + ":停下来了");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new VolatileTest().doWork();
                }
            }).start();
        }
        Thread.sleep(100);

        synchronized (VolatileTest.class){
            VolatileTest.shutdownRequested=true;
            System.out.println("---Stop---");
        }

    }
}
