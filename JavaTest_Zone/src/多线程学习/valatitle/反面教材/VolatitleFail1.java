package 多线程学习.valatitle.反面教材;

/**
 * Created by fuzhipeng on 2016/12/2.
 */
public class VolatitleFail1 {
    //TODO  volatile:保证了读取的值是正确的,
    //todo  但是++不是原子操作,那么并行的时候可能同时读取到race而不做真正的加1操作,
    //TODO  那么 同时读取的值 同时 在读取的值上+1 返回给race的时候 ,就相当于变少了;
    public  static volatile int race=0;

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 10000; i1++) {
                        race++;
                    }
                }
            }).start();
        }
        //当前活着的线程个数
       while(Thread.activeCount()>2)//大于两个我就等待 ,知道所有活着的<2就是1,就是main这个 就可以执行了
           //就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
           Thread.yield();
        System.out.println(race);
    }
}
