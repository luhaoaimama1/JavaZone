package ktshare.second.cls_

import a_新手.User
import java.io.Serializable

//https://www.kotlincn.net/docs/reference/data-classes.html
//我们经常创建一些只保存数据的类。 在这些类中，一些标准函数往往是从数据机械推导而来的。在 Kotlin 中，这叫做 数据类 并标记为 data
data class User(val name: String, val age: Int):Serializable
//编译器自动从主构造函数中声明的所有属性导出以下成员：
    //equals()/hashCode() 对；
    //toString() 格式是 "User(name=John, age=42)"；
    //componentN() 函数 按声明顺序对应于所有属性；
    //copy() 函数（见下文）。

//在 JVM 中，如果生成的类需要含有一个无参的构造函数，则所有的属性必须指定默认值。
data class User2(val name: String = "", val age: Int = 0)

fun main(args: Array<String>) {
    val jack = User(name = "Jack", age = 1)
//    val jack = User("Jack",1)
    //复制
// 在很多情况下，我们需要复制一个对象改变它的一些属性，但其余部分保持不变。
// copy() 函数就是为此而生成。对于上文的 User 类，其实现会类似下面这样：
//    val olderJack = jack.copy(age = 2)
//    val olderJack = jack.copy()
//    val (x,y)=User(name = "Jack", age = 1)
//    println(olderJack == jack)
}