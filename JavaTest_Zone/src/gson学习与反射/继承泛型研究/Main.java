package gson学习与反射.继承泛型研究;

/**
 * Created by fuzhipeng on 2017/4/27.
 */
public class Main {
    public static void main(String[] args) {
        new HelperExt2().heihei3().heihei6().heihei5();
        new HelperExt<HelperExt>().heihei().heihei3();
    }
}
