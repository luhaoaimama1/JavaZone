package kotlinHolder扩展

open class Holder3<T>(a:String) {
    var returnValue: T? = null
    init {
        println("init Holder3")
        returnValue=this as T
    }
    fun gogo(): T {
        println("gogo")
        return returnValue!!
    }
}

class Holder4(a:String) : Holder3<Holder4>(a) {

    init {
        println("init Holder4")
        returnValue=this
    }
    fun lala(): Holder4 {
        println("lala")
        return returnValue!!
    }
    fun lala2(): Holder4 {
        println("lala2")
        return returnValue!!
    }

}

open class Holder5<T>(a:String) : Holder3<T>(a) {

    init {
        println("init Holder4")
        returnValue=this as T
    }
    fun lala(): T {
        println("lalaHolder5")
        return returnValue!!
    }
    fun lala2(): T {
        println("lala2Holder5")
        return returnValue!!
    }

}
class Holder6(a:String) : Holder5<Holder6>(a) {

    init {
        println("init Holder4")
        returnValue=this
    }
    fun lalaHolder6(): Holder6 {
        println("lalaHolder6")
        return returnValue!!
    }
    fun lala2Holder6(): Holder6 {
        println("lala2Holder6")
        return returnValue!!
    }

}

fun main(args: Array<String>) {

    Holder6("4").gogo().lala().lala2().lala2Holder6().gogo()

}