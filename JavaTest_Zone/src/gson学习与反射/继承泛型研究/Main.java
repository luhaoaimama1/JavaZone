package gson学习与反射.继承泛型研究;

/**
 * Created by fuzhipeng on 2017/4/27.
 */
public class Main {
    public static void main(String[] args) {
        new HelperExt2().ex1hei1().ex2hei2().ex0hei1();
        new HelperExt<HelperExt>().ex0hei1().ex1hei1();
    }
}
