package 多线程学习.valatitle.other.同步块的详细理解;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//特殊理解三 锁方法快内部还需要相同的锁
public class SynchronizedExample3 {

    // 修饰一个类
    public static void test1(int j) {
        synchronized (SynchronizedExample3.class) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (SynchronizedExample3.class) {
                    System.out.println("test1 {" + j + "} - {" + i + "}");
                }
                method(j, i);
            }
        }
    }

    private static void method(int j, int i) {
        synchronized (SynchronizedExample3.class) {
            System.out.println("test1 哈哈 {" + j + "} - {" + i + "}");
        }
    }

//    // 修饰一个静态方法
//    public static synchronized void test2(int j) {
//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("test2 {" + j + "} - {" + i + "}");
//        }
//    }

    public static void main(String[] args) {
        SynchronizedExample3 example1 = new SynchronizedExample3();
        SynchronizedExample3 example2 = new SynchronizedExample3();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example2.test1(2);
        });
    }
}