package ktshare.second.cls_

class Outer {
    private val bar: Int = 1
    //inner修饰符
    inner class Inner {
        fun foo() = bar
    }
}

val demo = Outer().Inner().foo() // == 1

