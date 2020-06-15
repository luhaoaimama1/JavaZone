package gson学习与反射.反射

import gson学习与反射.反射框架测试.Reflect

object ReflectFinal {

    @JvmStatic
    fun main(args: Array<String>) {
       val main= Main()
        println("getName:" + Reflect.on(main).set("reflect",3))
        println("getName:" + Reflect.on(main).get("reflect"))

    }
}