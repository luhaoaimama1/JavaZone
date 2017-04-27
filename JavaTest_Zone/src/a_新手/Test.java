package a_新手;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2017/3/18.
 */
public class Test {
    int k = 1;

    public Test() {
        System.out.println(k);
    }

    public static void main(String[] args) {
//        new Test();
        List<String> list=new ArrayList<>();

        list.add(null);
        list.add("1");
        System.out.println( list.size());

    }
}
