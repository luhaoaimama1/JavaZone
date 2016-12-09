package 语法糖;

/**
 * Created by fuzhipeng on 2016/12/2.
 */
public class 自动装箱陷阱 {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        //todo  为什么e==f 是false
        //原因 这是源码中的 Integer 中 valueOf，也就是说cache中已有-128到127，不在这范围的会新new ，这时可以理解比较是内存地址
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }
}
