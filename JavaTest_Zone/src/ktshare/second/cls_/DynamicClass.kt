package ktshare.second.cls_

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base,val a: Base) : Base by a{
    override fun print() {
        println("before")
        a.print()
        println("after")
    }
}

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    val a = BaseImpl(11)
    Derived(b,a).print()
}