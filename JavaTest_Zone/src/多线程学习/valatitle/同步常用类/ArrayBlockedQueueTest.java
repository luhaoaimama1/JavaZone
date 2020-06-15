package 多线程学习.valatitle.同步常用类;

import java.util.concurrent.ArrayBlockingQueue;
//http://www.dongwang0623.cn/2019/12/10/androidbasic/android-arrayblockingqueue-shi-yong/
public class ArrayBlockedQueueTest {
    public static void main(String[] args) {
        //调用两个线程来跑
        ArrayBlockedQueueTest blockQueue = new ArrayBlockedQueueTest();
        blockQueue.startProducerThread();
        blockQueue.startConsumerThread();
    }

    public static final String TAG = "Test.BlockedQueue";
    public static final int SIZE = 2;
    public volatile ArrayBlockingQueue<String> blockedQueue = new ArrayBlockingQueue<>(SIZE, true);
    private int num = 0;


    //生产者线程
    public void startProducerThread() {
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    feedData();
                }
            }
        });
        producerThread.start();
    }

    //消费者线程
    public void startConsumerThread() {
        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getData();
                }
            }
        });
        consumerThread.start();
    }

    public void feedData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (blockedQueue.size() >= SIZE) {
            System.out.println("feedData size ==  " + blockedQueue.size());
            blockedQueue.remove();
        }
        String str = "String:" + num;
        blockedQueue.add(str);
        System.out.println("feedData: " + str);
        num++;
    }

    public void getData() {
        try {
            String str = blockedQueue.take(); //這裏應該使用take()阻塞
            if (null == str) {
                System.out.println("getData poll null ..." + blockedQueue.size());
                return;
            }
            System.out.println("getData: " + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}