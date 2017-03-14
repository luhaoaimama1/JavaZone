package a面试;

/**
 * Created by fuzhipeng on 2017/3/11.
 */
public class 原理i自加 {

    public static void main(String[] args) {

        int i=0;


        i=i++; //-------相当于下面三行

        System.out.println(i);

        //用了中间变量缓存机制；
        int temp=i;
        i=i+1;
        i=temp;
        float s=1.0F;

    }

}
