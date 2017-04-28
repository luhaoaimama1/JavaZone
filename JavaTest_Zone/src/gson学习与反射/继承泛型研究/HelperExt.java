package gson学习与反射.继承泛型研究;

/**
 * Created by fuzhipeng on 2017/4/27.
 */
public  class HelperExt<B extends HelperExt> extends Helper<B> {

    public HelperExt() {
        this.child = child;
    }

    public B ex1hei1() {
        return child;
    }

    public B ex1hei2() {
        return child;
    }
}
