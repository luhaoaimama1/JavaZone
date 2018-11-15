package kt

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by fuzhipeng on 2018/7/9.
 */

fun main(args: Array<String>) {
//    loop()
//    other()
//    whenStudy()
//    解构()
//    mapLoop()
//    delege范例()

//    方法引用(numbers)
//    扩展方法()

//    list常用方法()


//    val list = listOf("args:", args)
//    println(list)
//    var list = ArrayList<Int>()
//    list.add(1)
//    list.add(2)
//    list.add(3)
//    var list2 = list
//    list.clear()
//    println(list2)
//    if(null is String)
//        print("heieh")
//    else
//        print("222")

    listOf<Float>(-251F,-242F,-119F,-35F,23F,27F,23F,9.6F,0F).forEach {
        print("${MathUtils.linearMap(it ,-251F,0F,-1080F,0F)},")
    }
}



object MathUtils {
    //    t, tMin, tMax, value1, value2
    fun linearMap(srcNow: Float, src1: Float, src2: Float, dst1: Float, dst2: Float): Float {
        return (srcNow - src1) * (dst2 - dst1) / (src2 - src1) + dst1
    }

    fun linearMap(srcNow: Double, src1: Double, src2: Double, dst1: Double, dst2: Double): Double {
        return (srcNow - src1) * (dst2 - dst1) / (src2 - src1) + dst1
    }
}

private fun list常用方法() {
    val numbers = listOf<Int>(100, -1, 2, 3, 4);

    //过滤
    println(numbers.filter { it > 0 }.toString())

    val isTrue: (Int) -> Boolean = { it > 0 }
    //整体判定
    println("any ||的意思->" + numbers.any(isTrue).toString())
    println("all &&的意思->" + numbers.all(isTrue).toString())
    //find
    println("find  返回的是元素 而不是index->" + numbers.find(isTrue).toString())
    println("count  个数->" + numbers.count(isTrue).toString())

    //大小
    listOf(1, 42, 4).max() == 42
    listOf("a", "ab").minBy { it.length } == "a"

    //统计 和
    listOf(1, 5, 3).sum() == 9
    listOf("a", "b", "cc").sumBy { it.length } == 4

    //排序
    listOf("bbb", "a", "cc").sorted() == listOf("a", "bbb", "cc")
    listOf("bbb", "a", "cc").sortedBy { it.length } == listOf("a", "cc", "bbb")

    //转化
    println("flatMap  分裂 后合并->" + listOf("abc", "12").flatMap { it.toList() })
    //分组
    listOf("a", "b", "ba", "ccc", "ad").groupBy { it.length }
    //分裂
    val numbers4 = listOf(1, 3, -4, 2, -11)
    val (positive, negative) = numbers4.partition { it > 0 }
    positive == listOf(1, 3, 2)
    negative == listOf(-4, -11)
}

private fun 扩展方法() {
    println(Pair(1, 3).sum_())
}

fun Int.sum_():Boolean=this % 2 == 0

fun Int.sum_2():Boolean{ return  this % 2 == 0 }

val sum2: Int.() -> Boolean = { this % 2 == 0 }

fun Pair<Int, Int>.sum_(): Int = first + second

private fun 方法引用(numbers: List<Int>) {
    numbers.filter(::isOdd)
    numbers.filter(fun(x: Int): Boolean = x % 2 == 1)
    numbers.filter { x -> x % 2 == 1 }
    numbers.filter {
        x ->
        x % 2 == 1
    }
    val compose = compose(::length, ::isOdd)
    println("zone:" + compose("zone"))

}

fun isOdd(value: Int) = value % 2 == 1;
fun length(s: String) = s.length

fun <A, B, C> compose(g: (A) -> B, f: (B) -> C): (A) -> C {
    return { x -> f(g(x)) }
}


private fun delege范例() {

    var example = Example()
    println(example.name)
    example.name = "哈哈"


    var lazyObj = LazyExample()
    println("------------")
    println(lazyObj.name)
    println(lazyObj.name)

    var observeObj = ObserveExample()
    println("------------")
    observeObj.name = "wakaka"
    observeObj.name = "安静"


    val user = User()
//     user.name //-> IllegalStateException
    user.init("Carl")
    println(user.name)

    var userMap = UserMap(mapOf(
            "name" to "zone",
            "skill" to 222,
            "skill2" to "大大大2"
    ));
    println(userMap)
    println("name :${userMap.name} \t skill :${userMap.skill}")
}

data class UserMap(val map: Map<String, Any?>) {
    val name: String by map
    val skill: Int by map
}

class User {
    var name: String by Delegates.notNull()

    fun init(name: String) {
        this.name = name
    }
}

class ObserveExample {
    var name by Delegates.observable("what?") {
        property, oldValue, newValue ->
        println("${property.toString()} - $oldValue - $newValue")
    }
}

class LazyExample {
    val name by lazy {
        println("懒加载初始化中")
        "zone"
    }
}

class Example {
    var name: String by Delegate()

}

class Delegate {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("$value has been assigned to ${prop.name} in $thisRef")
    }
}


private fun mapLoop() {
    var map = hashMapOf<String, String>()
    map.put("haha", "gaga")
    map.put("?", "_")
//    for (mutableEntry in map) // mutableEntry可以解构
    for ((key, value) in map) {
        println("{key=$key,value=$value}")
    }
}

private fun 解构() {
    val (name, skills) = Animal("鸭子", "嗑瓜子")
    val (name2, skills2) = Animal2("鸭子", "嗑瓜子")
    print("name:$name \t name2:$name2")
    print("skills:$skills \t skills2:$skills2")


    println(Animal("鸭子", "嗑瓜子"))
    var ani = Animal("鸭子", "嗑瓜子")
    var ani2 = ani.copy(skills = "嗑瓜子2")
    println("真的相等吗：${ani === ani2} ani skills=${ani.skills} \t copy skills=${ani2.skills}")

    var ani3 = ani.copy()
    println("copy 不改名字 equal？${ani3.equals(ani)}")
}

private fun other() {
    println(MB::name.get())

    Student("Lucky").showName()

    println(Utils.max(1, 2))

    println("空吗->${Utils.parseInt("干啥？")}")

    println("空吗2->${Utils.getStringLength("Lucky")}")
}

private fun loop() {
    var args2 = listOf<String>("1", "2");
    println("Hello:${args2[0]}")

    for (s in args2) {
        println("嘿嘿:$s!")
    }
    for (s in args2.indices) {
        print("\t ${args2[s]}")
    }
    println()

    for (i in 1 until 10) {
        print("\t $i")
    }
    println()

    val array = arrayListOf<String>()
    array.add("1")
    array.add("2")

    if (10 in 1..10)
        print("10在区间内吗")

}

private fun whenStudy() {
    Utils.case(1)
    Utils.case(4)
    Utils.case("abc")
    Utils.case(Student("Lucky"))
}

class Student(val name: String) {
    fun showName() = print("我的名字:$name")

}

object Utils {
    fun max(a: Int, b: Int) = if (a > b) a else b

    fun parseInt(str: String): Int? {
        try {
            return str.toInt()
        } catch(e: Exception) {
            return null;
        }
    }

    fun getStringLength(obj: Any): Int? {
        if (obj is String)
            return obj.length
        return null
    }

    fun case(obj: Any) {
        when (obj) {
            1 -> println("1")
            in 3..5 -> println("在3-5里")
            is String -> println("是字符串")
            else -> println("是其他类型")
        }
    }

}

data class Animal(val name: String, val skills: String)

class Animal2(val name: String, val skills: String) {
    operator fun component1() = name
    operator fun component2() = skills
}