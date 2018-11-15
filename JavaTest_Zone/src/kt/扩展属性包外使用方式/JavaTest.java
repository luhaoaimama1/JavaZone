package kt.扩展属性包外使用方式;
import kt.扩展.*;

import java.util.ArrayList;

/**
 * Created by fuzhipeng on 2018/11/15.
 */
public class JavaTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        methodTest(list);
        fieldTest(list);
    }

    private static void fieldTest(ArrayList<Integer> list) {
        System.out.println("/*************fieldTest*************/");
        System.out.println(属性扩展Kt.getLastIndex("abc"));
        System.out.println(属性扩展Kt.getLastObj(list));
    }

    private static void methodTest(ArrayList<Integer> list) {
        System.out.println("/*************methodTest*************/");
        System.out.println(方法扩展Kt.second(list));
        System.out.println(方法扩展Kt.twoCount("abc"));
    }
}
