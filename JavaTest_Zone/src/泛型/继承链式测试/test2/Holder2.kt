package 泛型.继承链式测试.test2


/**
 * Created by fuzhipeng on 2019/3/5.
 */


abstract class Holder2<out B : Holder> : Holder1<B>() {
    fun go2(): B {
        return getReturnValue()
    }
}


