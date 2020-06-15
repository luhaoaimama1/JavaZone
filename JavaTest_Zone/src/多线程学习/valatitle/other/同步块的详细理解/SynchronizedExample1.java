package 多线程学习.valatitle.other.同步块的详细理解;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//修饰代码块和方法：
public class SynchronizedExample1 {

    // 修饰一个代码块
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("test1 {" + j + "} - {" + i + "}");
            }
        }
    }

    // 修饰一个方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test2 {" + j + "} - {" + i + "}");
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
//        //一
//        executorService.execute(() -> {
//            example1.test1(1);
//        });
//        executorService.execute(() -> {
//            example1.test1(2);
//        });
//        //二
//        executorService.execute(() -> {
//            example2.test2(1);
//        });
//        executorService.execute(() -> {
//            example2.test2(2);
//        });
//        //三
//        executorService.execute(() -> {
//            example1.test1(1);
//        });
//        executorService.execute(() -> {
//            example2.test1(2);
//        });

        //四
        executorService.execute(() -> {
            example1.test1(1);

        });
        executorService.execute(() -> {
            example1.test2(2);

        });
    }
}