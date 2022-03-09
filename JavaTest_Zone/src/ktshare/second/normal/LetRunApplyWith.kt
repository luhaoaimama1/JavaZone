package ktshare.second.normal

fun main(args: Array<String>) {

    //let:it代表引用。 最后一行为返回值 ：主要用于返回另一个值
    val message = "abc".let {
        "==>${it.length}"
        1
    }
    println(message)

    //apply:this代表引用 可以省略这就是优点。 return this; 主要用于执行操作
    val message1 = "abc".apply {

        println(length)
        "==>"
        1
    }
    println(message1)

    //run:this代表引用。最后一行为返回值; 相对于不同于let的是 引用是this 可以省略 。
    val message2 = "abc".run {
        "==>"
        1
    }
    println(message2)

    //个人不用这个
    println(with("abc") {
        "==>"
    })
}