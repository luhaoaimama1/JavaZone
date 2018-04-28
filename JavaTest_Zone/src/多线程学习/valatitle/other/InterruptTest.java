/**
 * @functon 多线程学习 yield
 * @author 林炳文
 * @time 2015.3.9
 */
package 多线程学习.valatitle.other;


public class InterruptTest {
    //   当前线程可转让cpu控制权，让别的就绪状态线程运行（切换）
    //实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
    //此例子，张三上来就让出cpu时间。让李四执行完毕后，
    public static void main(String[] args) {

        ThreadYield yt1 = new ThreadYield("张三");
        ThreadYield2 yt2 = new ThreadYield2("李四");
        yt1.start();
        yt2.start();
    }

    static class ThreadYield extends Thread {
        public ThreadYield(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println("" + this.getName() + "--打断！---" + i);
                interrupt();
            }

        }
    }

    static class ThreadYield2 extends Thread {
        public ThreadYield2(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                System.out.println("" + this.getName() + "--打断！---" );
                interrupt();
            }
        }
    }
}