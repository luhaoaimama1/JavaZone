package 泛型.继承链式测试.test2


/**
 * Created by fuzhipeng on 2019/3/5.
 */



class Holder3 : Holder2<Holder3>() {
    override fun getReturnValue(): Holder3 = this

    fun go3(): Holder3 {
        return getReturnValue()
    }

}


