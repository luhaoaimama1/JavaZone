package classload

//https://blog.csdn.net/briblue/article/details/54973413
object Jvm {
    @JvmStatic
    fun main(args: Array<String>) {
        println("bootClassPath====>")
        for (c in System.getProperty("sun.boot.class.path").split(";")) {
            println(c)
        }

        println("ExtClassLoader====>")
        for (c in System.getProperty("java.ext.dirs").split(";")) {
            println(c)
        }

        println("AppClassLoader====>")
        for (c in System.getProperty("java.class.path").split(";")) {
            println(c)
        }

        //获取classLoader的方式
        println( Thread.currentThread().contextClassLoader)

        println(Jvm::class.java.classLoader)
        //这个Jvm类是我们自己编写的，那么int.class或者是String.class的加载是由谁完成的呢？
        //return null! 意思是int.class这类基础类没有类加载器加载？
        // 当然不是！int.class是由Bootstrap ClassLoader加载的。要想弄明白这些，我们首先得知道一个前提。
        println(Int::class.java.classLoader)


        println("AppClassLoader 的所有父亲")
        var classLoader = Jvm::class.java.classLoader
        while(classLoader!=null){
            println("parent"+classLoader.parent)
            classLoader=classLoader.parent
        }
        //父加载器不是父类

        println("systemClassLoader==>${ClassLoader.getSystemClassLoader()}")
    }
}