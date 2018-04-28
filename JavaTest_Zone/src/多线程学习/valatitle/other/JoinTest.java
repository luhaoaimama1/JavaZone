package 多线程学习.valatitle.other;

/**
 * Created by fuzhipeng on 2018/4/23.
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        Thread b = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 0; i < 5; i++) {
                    System.out.println("我是子线程 " + i);
                    if(i==2)
                        Thread.currentThread().interrupt();
                    try {
//                        Thread.currentThread().wait(300);
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        b.start();
        //是 b线程执行完毕后  继续父线程下面的代码  join后边加时间的花 就是 超过这个时间 父线程中下面的人物就不等待了 不管你完成否
        b.join();
        System.out.println("我是父线程");
    }
}
