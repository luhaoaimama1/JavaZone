package 多线程学习.valatitle.锁;

public class TestLock {

    private final Object lock = new Object();

    public static void main(String[] args) {
        new TestLock().lock1();
    }
    public void lock1() {
        System.out.println("lock1");
        synchronized (lock) {
            lock2();
        }
        lock2();
    }

    public void lock2() {
        synchronized (lock) {
            System.out.println("lock2");
        }
    }
}
