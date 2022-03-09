package ktshare.second.method_

class MethodParName {
    fun a(a: String, b: String, c: String, d: String = "haha") {
        println("a:$a \t b:$b \t c:$c \t d:$d")
    }
}

fun main(args: Array<String>) {
//    MethodParName().a(a = "a", b = "b", c = "d")
    MethodParName().a(b = "b", a = "a", c = "d")
}