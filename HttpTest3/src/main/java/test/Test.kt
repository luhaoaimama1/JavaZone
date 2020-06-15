package test

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
//        for (i in 0..100) {
//            println(add(i,1))
//        }

        println( 1/4L)
    }


    fun add(a:Int,b:Int):Int{
//        println("逻辑前面")
        val i = a + b
//        println("逻辑后面")
        return i
    }
}