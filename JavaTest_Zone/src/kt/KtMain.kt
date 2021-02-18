package kt

import java.lang.StringBuilder
import java.text.DecimalFormat

/**
 * Created by fuzhipeng on 2018/7/7.
 */
fun main(args: Array<String>) {
//    InitOrderDemo("zone");

//    Constructors(name2 = "zone", name = "kb").print()

//    MB.print()
    val sum = 9
    val now = 1
    val message = getGallaryText(sum, now)

    println(message)
//    println("$c/t ${(c + now).toString().subSequence(1, b + 1)}")
}

private fun getGallaryText(sum: Int, now: Int): String {
    val length = sum.toString().length
    val nowLength = now.toString().length
    val sb = StringBuilder()
    for (i in 0 until length - nowLength) {
        sb.append("0")
    }
    sb.append(now)
    return  "${sb.toString()} / $sum"
}

fun pow(a: Int, b: Int): Int {
    var result = 1;
    for (i in 0 until b) {
        result = result * a
    }
    return result
}

object MB : Constructors(name = "我欧式单利") {

}

class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

open class Constructors constructor(name: String) {
    val name = name;
    var name2: String = ""

    var counter = 0 // 注意：这个初始器直接为幕后字段赋值
        get() = counter
        set(value) {
            if (value >= 0) field = value
        }

    var counter2: String = "a" // 注意：这个初始器直接为幕后字段赋值
        get() = counter2
        set(value) {
            field = counter2
        }


    constructor(name: String, name2: String) : this(name2) {
        this.name2 = name;
    }

    fun print() = print("name=$name,name2=$name2")
}

class ChildConstructors(name: String, name2: String) : Constructors(name2) {

}
