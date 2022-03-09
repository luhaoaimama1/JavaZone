package ktshare.second.field_

class LazyField {
    val abc: View by lazy {
        View()
    }

}

fun main(args: Array<String>) {
    println(LazyField().abc)
}