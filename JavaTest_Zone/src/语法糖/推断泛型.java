package 语法糖;

/**
 * Created by fuzhipeng on 2016/12/9.
 */
public class 推断泛型 {

    public static void main(String[] args) {
        Integer i = get(new Child1());
    }

    private static <T> T get(Par v) {
        return (T) v;
    }

    public static class Par {

    }

    public static class Child1 extends Par {

    }

    public static class Child2 extends Par {

    }


}
