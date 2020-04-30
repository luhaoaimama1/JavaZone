package 多线程学习.valatitle.other;

public class SynamicMethodTest {
    Object lock = new Object();

    public static void main(String[] args) {
        SynamicMethodTest test = new SynamicMethodTest();
        test.add(2);
        test.add(5);
        test.deal(3);
    }

    public void add(int i) {
        synchronized (lock) {
            System.out.println("add"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deal(i);
        }
    }

    public void deal(int i) {
        synchronized (lock) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}
