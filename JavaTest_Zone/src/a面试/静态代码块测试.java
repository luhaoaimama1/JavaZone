package a面试;

/**
 * Created by fuzhipeng on 2017/3/11.
 */
public class 静态代码块测试 {
    static{
         x=5;//在前面的时候可以赋值成功的  但是在前面的时候不能引用
    }
    private  static int x;
    static {
        x=67;
    }
    static {
        int x=99;//因为这个是局部变量 为什么因为  他用int声明了
    }

    public static void main(String[] args) {
        System.out.println(x);

    }
}
