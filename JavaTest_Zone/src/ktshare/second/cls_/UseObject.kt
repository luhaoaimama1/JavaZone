package ktshare.second.method_

interface IClick {
    fun click(v: String)
}

//单例模式在一些场景中很有用， 而 Kotlin（继 Scala 之后）使单例声明变得很容易：
//这称为对象声明。并且它总是在 object 关键字后跟一个名称。 就像变量声明一样，对象声明不是一个表达式，不能用在赋值语句的右边。
//对象声明的初始化过程是线程安全的并且在首次访问时进行
//如需引用该对象，我们直接使用其名称即可：DataProviderManager.registerDataProvider
object DataProviderManager {
    fun registerDataProvider() {
        println("registerDataProvider")
    }
}


fun main(args: Array<String>) {
    //匿名内部类
    val value = object : IClick {
        override fun click(v: String) {
            println("do${v}")
        }
    }
    value.click("click")

    //简单的对象
    val adHoc = object {
        var x: Int = 0
        var y: Int = 2
    }
    print(adHoc.x + adHoc.y)

    //使用单例的方法
    DataProviderManager.registerDataProvider()
}
