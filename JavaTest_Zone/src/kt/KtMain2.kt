package kt

import org.apache.commons.lang.ObjectUtils
import java.io.File

/**
 * Created by fuzhipeng on 2018/7/7.
 *
 * todo let方法？value?.let { transformValue(it) } ?: defaultValueIfValueIsNull
 */

/**
 *[2018] by Zone
 */

//val 一次赋值（只读）
val a = 1;
// var 变量
var count: Long = 0;
//设置的话 会自动推断
var count2 = 0;

class Gaga{
    fun gogo(){
        print("我是类中地方")
    }
}

fun printSum(a: Int, b: Int) {
    //字符串中  美元符号 取值
    println("sum of $a and $b is ${a + b}")

//    var a = 1;
//    when (a) {
//        in 1..10 -> print("1-10");
//        else -> print("1-10");
//    }


}

//推断函数
fun addSum(var1: Int, var2: Int) = var1 + var2;


fun main(args: Array<String>) {
//    printSum(1, 2);


//    val files = File("Test").listFiles()
    val files = emptyArray<String>();


    println(files?.elementAtOrNull(0)?:"空啊")

    var file2:String?="ab" ;
    file2?.let { println("${it}干啊") }

}

//? 表示 可控  没有？则不能return null;
fun getStringLength(obj: Any): Int? {
    // `obj` 在 `&&` 右边自动转换成 `String` 类型
    if (obj is String && obj.length > 0) {
        return obj.length
    }

    return null
}

fun main2(args: Array<String>) {
    val items = listOf("apple", "banana", "kiwifruit")
    //相当于for
    for (item in items) {
        println(item)
    }
    // 相当于 fori
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}