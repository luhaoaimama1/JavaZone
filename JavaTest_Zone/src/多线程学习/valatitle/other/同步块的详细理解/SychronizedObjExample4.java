package 多线程学习.valatitle.other.同步块的详细理解;

public class SychronizedObjExample4 {
    //不同方法 也会被锁限制住，类似只有一个钥匙 想要进门得等钥匙被释放才能用
    private final Object lock = new Object();

    public void open() throws InterruptedException {
        System.out.println("wating 进入 open");
        synchronized (lock) {
            System.out.println("进入 open");
            Thread.sleep(3000);
            System.out.println("退出 open");
        }
    }

    public void close()  throws InterruptedException {
        System.out.println("wating 进入 close");
        synchronized (lock) {
            System.out.println("进入 close");
            Thread.sleep(3000);
            System.out.println("退出 close");
        }
    }

    public static void main(String[] args) {

        SychronizedObjExample4 item = new SychronizedObjExample4();
        new Thread(() -> {
            try {
                item.open();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                item.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
