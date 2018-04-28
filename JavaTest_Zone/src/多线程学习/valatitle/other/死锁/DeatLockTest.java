package 多线程学习.valatitle.other.死锁;

/**
 * Created by fuzhipeng on 2018/4/23.
 * 死锁文章： http://www.cnblogs.com/xrq730/p/4853713.html
 */
public class DeatLockTest {


    /**
     * 两个对象锁。 两个同步块，第一个同步快，用不一样的锁。等待一会 都进来了 既都持有了。
     * 然后在进第二个同步块。这个时候需要的对象锁别互相持有 这时候就 成死锁了
     * @param args
     */
    public static void main(String[] args) {
        DeadLock dl = new DeadLock();
        Thread0 t0 = new Thread0(dl);
        Thread1 t1 = new Thread1(dl);
        t0.start();
        t1.start();

        while (true) ;
    }
}
