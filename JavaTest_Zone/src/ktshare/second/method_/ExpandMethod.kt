package ktshare.second.method_


//@JvmStatic
fun String.sizeExpand(a:Int): Int {
    return this.length+a
}
object ExpandMethod {

    fun  abc(){

    }
}


fun main(args: Array<String>) {
//    val expandMethod = ExpandMethod
//    expandMethod.abc()
    println("abc".sizeExpand(3))
}