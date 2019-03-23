package 泛型.继承链式测试.test2

/**
 * Created by fuzhipeng on 2019/3/5.
 */

abstract class Holder

abstract class Holder1<out T :Holder>: Holder() {

    abstract fun getReturnValue(): T

    fun go(): T {
        return getReturnValue()
    }

}