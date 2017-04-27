package gson学习与反射.继承泛型研究;

/**
 * Created by fuzhipeng on 2017/4/27.
 */
public  class HelperExt<B extends HelperExt> extends KBB.Helper<B> {

    public HelperExt() {
        this.child = child;
    }

    public B heihei3() {
        return child;
    }

    public B heihei4() {
        return child;
    }
}
