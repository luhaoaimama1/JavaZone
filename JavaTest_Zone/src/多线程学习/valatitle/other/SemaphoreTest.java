package 多线程学习.valatitle.other;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) {

        SemaphoreService service = new SemaphoreService(); // 使用总 6 通路，每个线程占用2通路
        for (int i = 0; i < 10; i++) {
            MyThread t = new MyThread("thread" + (i + 1), service);
            t.start();// 这里使用 t.run() 也可以运行，但是不是并发执行了
        }
    }

    public static class MyThread extends Thread {
        private SemaphoreService service;

        public MyThread(String name, SemaphoreService service) {
            super();
            this.setName(name);
            this.service = service;
        }

        @Override
        public void run() {
            this.service.doSomething();
        }
    }

    public static class SemaphoreService {
        // 同步关键类，构造方法传入的数字是多少，则同一个时刻，能同时运行多少个通路数
        private Semaphore semaphore = new Semaphore(1);

        public void doSomething() {
            try {
                /**
                 * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
                 * 因为semaphore的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
                 * */
                semaphore.acquire();//需要一个通路
                System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();//释放一个通路
            }
        }

        public String getFormatTimeStr() {
            return sf.format(new Date());
        }
    }
}
