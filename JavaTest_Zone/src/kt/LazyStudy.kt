package kt

class LazyStudy {
    var pop: String? = null
    fun initPop():String? {
        if (pop == null) {
            pop = "haha"
        }
        val callback=A@{
            println("callback1")
            return@A
        }
        callback.invoke()
        println("callback2")
        return pop
    }
}

fun main(args: Array<String>) {
    val lazyStudy = LazyStudy()
    lazyStudy.initPop()
    println("before ___pop:${lazyStudy.pop}")
    lazyStudy.pop = null
    println("after ___pop:${lazyStudy.pop}")
}