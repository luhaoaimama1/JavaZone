package kt

/**
 * Created by fuzhipeng on 2018/7/7.
 */
fun main(args: Array<String>) {
//    InitOrderDemo("zone");

    Constructors(name2 = "zone", name = "kb").print()

    MB.print()
}


object MB:Constructors(name = "我欧式单利") {

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
        get()=counter
        set(value) {
            if (value >= 0) field = value
        }

    var counter2 : String = "a" // 注意：这个初始器直接为幕后字段赋值
        get()=counter2
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
