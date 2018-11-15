package kt.扩展属性包外使用方式


//外部使用 需要导入引用 。声明在顶层的话 导入包名加类方法即可！
import kt.扩展.*

/**
 * Created by fuzhipeng on 2018/11/15.
 */
fun main(args: Array<String>) {
    methodTest()
    fieldTest()
}

fun fieldTest() {
    println("/*************fieldTest*************/")
    println("abc".lastIndex)
    println(arrayListOf<Int>(1,3).lastObj)
}

fun methodTest() {
    println("/*************methodTest*************/")
    println("abc".twoCount())
    println(arrayListOf<Int>(1,3).second())
}
