package ktshare.second.normal


fun main(args: Array<String>) {
    val s: String? = null
    val s1 = s ?: "abc"
    println(s1)

    val a: String = "a"
    val a1 = a ?: "abc"
    println(a1)
}