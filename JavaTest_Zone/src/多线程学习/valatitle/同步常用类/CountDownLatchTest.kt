package 多线程学习.valatitle.同步常用类

import java.util.concurrent.CountDownLatch
//https://www.cnblogs.com/coolgame/p/8746750.html
object CountDownLatchTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val c = CountDownLatch(3) //总数3
        Thread(Runnable {
            try {
                println("开始等")
                c.await() //阻塞，等待countDown，当countDown到0就执行后面的完事了
                println("first wait 唤醒")
                c.await() //阻塞，等待countDown，当countDown到0就执行后面的完事了
                println("secOnd wait 唤醒")
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }).start()

        Thread(Runnable {
            for (i in 3 downTo 1) {
                println("开始i  ${i} \t c.count:${c.count}")
                c.countDown() //减1
            }
            println("沉睡ing")
           Thread.sleep(1000)
            for (i in 3 downTo 1) {
                println("开始i  ${i} \t c.count:${c.count}")
                c.countDown() //减1
            }
        }).start()
    }
}