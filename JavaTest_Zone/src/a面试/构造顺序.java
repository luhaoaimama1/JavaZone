package a面试;

/**
 * Created by fuzhipeng on 2017/3/11.
 */
public class 构造顺序   {
    public static void main(String[] args) {
        //先在 内存new一个对象 ，然后把变量赋值。然后走构造器内的方法；
        new G();
    }
    //先走继承
    static class G extends X{
        Y y=new Y();
        public G() {
            System.out.println("G");
        }
    }
    static  class X{
        Y y=new Y();
        public X() {
            System.out.println("X");
        }
    }
    static class Y{
        public Y() {
            System.out.println("Y");
        }
    }
}
