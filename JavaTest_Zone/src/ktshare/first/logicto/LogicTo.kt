package ktshare.first.logicto

import antlr.build.ANTLR

/**
 * While是和java一样的
 * 而Break 与 continue 也是一样的
 */

/**
 *if 的分支可以是代码块，最后的表达式作为该块的值：
 * ps:kotlin没有三目运算符  只能用if else 代替三目运算符
 */
fun ifFun(a: Int, b: Int): Int {
    return if (a > b) {
        println("xxx")
        a
    } else {
        b
    }
}

/**
 * when 表达式取代了switch 语句。其最简单的形式如下：
 * 和if 一样 是可以有返回值的
 *
 * 参数去顺序匹配分支条件 如果符合则不会向下执行 走对应的{}的逻辑
 * when(参数){
 *  分支条件1->{}
 *  分支条件2->{}
 *  else->{}
 * }
 */
fun whenFun(x: Int): String {
    return when (x) {
        2 -> "2"
        1, 3 -> "1 or 3" //多个条件
        in 1..10 -> "x is in the range"
        !in 10..20 -> "x is outside the range"
        else -> { // 注意这个块
            "else"
        }
    }
}

/**
 * for 可以循环遍历任何提供了迭代器的对象。
 * 有一个成员函数或者扩展函数 iterator()，它的返回类型
 *  有一个成员函数或者扩展函数 next()，并且
 *  有一个成员函数或者扩展函数 hasNext() 返回 Boolean。
 */
fun forFun() {

    //1..3也是IntRange：IntProgression：Iterable
    for (i in 1..3) {
        println(i)
    }
    for (i in 6 downTo 0 step 4) {
        println(i)
    }

    val listOf = listOf<Int>(1, 2, 3)
    for (i in listOf) {
        println(i)
    }
    for (i in listOf.indices) {//
        println(listOf[i])
    }
}
fun main(args: Array<String>) {
    forFun()

//    val listOf = listOf(1L, 3, 4L)
//    listOf.
    val listOf = listOf("13", 1)
//    listOf[1]=1
//    listOf.toMutableList()
    val mutableListOf = mutableListOf<Any>("13", 1)
    mutableListOf.set(0,"14")

    val listOfNotNull:List<Any?> = listOfNotNull(null, 1, 3, 4)
//    for (any in listOfNotNull) {
//
//    }
    println("=<$listOfNotNull")
    val toMutableList = listOfNotNull.toMutableList()
    toMutableList.add(0,null);
    println("=<$toMutableList")

//    val arrayList = ArrayList<Any>()
//    arrayList.addAll(listOf)

    var abc:String?=null
//    val length = abc?.length
//    val s = abc ?: return
    abc!!.length
}