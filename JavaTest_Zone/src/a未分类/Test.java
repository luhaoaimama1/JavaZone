package a未分类;

import kt.Kt2Static;
import kt.StaticTES;
import kt.StaticTESKt;
import kt.UserUtil;

public class Test {
    static {
        i = 0;
    }

    static int i = 1;

    public static void main(String[] args) {
        System.out.println(i);
        StaticTES.Companion.haha();
        StaticTESKt.gaga();
        UserUtil.INSTANCE.XXX();
    }
}