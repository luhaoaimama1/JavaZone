package a_新手;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fuzhipeng on 2017/3/18.
 */
public class Test3 {
    int k = 1;

    String name = "default";

    public Test3() {
        System.out.println(k);
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        long b = new Date().getTime();
        System.out.println(b - a);
//        Test3 test = new Test3();
//        System.out.println(test);
//        setName(test);
//        System.out.println(test);
//        System.out.println(test.name);
    }

    public static void setName(Test3 test3) {
//        test3=new Test3();
        test3.name = "xin";
    }
}
