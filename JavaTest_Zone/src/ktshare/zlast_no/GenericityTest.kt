package ktshare.zlast_no

//todo 解开注解 看看报错
interface Source<in T> {
    fun nextT(a: T): String
}

interface Source2<out T> {
    fun nextT(a: String): T
}

interface Source3<T : String> {
    fun nextT(a: T): T
}
//
//interface Source5<out T> {
//    fun nextT(): T
//}
//
//class GenericityTest<T> {
//    var a: ArrayList<Source5<T>> = ArrayList()
//    fun add(p1: Source5<T>) {
//        a.add(p1)
//    }
//}
//
//fun main(args: Array<String>) {
//    val fanxing = GenericityTest<Any>()
//    val value = object : Source5<String> {
//        override fun nextT(): String {
//            return "Exmaple()"
//        }
//    }
//    fanxing.add(value)
//
////  实在用不好就强转 as?
//}

