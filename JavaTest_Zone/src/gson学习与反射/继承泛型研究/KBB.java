package gson学习与反射.继承泛型研究;

/**
 * Created by fuzhipeng on 2017/4/27.
 */
public class KBB {

    public static class Helper<B extends Helper> {
        protected B child;

        public Helper() {
            this.child = child;
        }

        public B heihei() {
            return child;
        }

        public B heihei2() {
            return child;
        }
    }
}
