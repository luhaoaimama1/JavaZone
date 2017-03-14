package a面试;

/**
 * Created by fuzhipeng on 2017/3/12.
 */
public class String编译研究 {
    public static void main(String[] args) {
        String s="h"+"e"+"x";
        String M="h"+s;
        String value=new String("world");
        String value1="world";
        System.out.println(value==value1);
        value.intern();
        System.out.println(value==value1);
    }
}
