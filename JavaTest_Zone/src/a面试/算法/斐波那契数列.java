package a面试.算法;
/**
 * Created by fuzhipeng on 2017/3/11.
 */
public class 斐波那契数列 {
    public static void main(String[] args) {

        for (int i = 1; i < 10; i++)
            System.out.println( "i:"+i+"   _result:"+count(i));
    }

    private static int  count(int i) {
        if(i<=0)
            throw new IllegalArgumentException("参数不能小于0");
        if(i==1||i==2)
            return 1;
        return count(i-1)+count(i-2);
    }
}
