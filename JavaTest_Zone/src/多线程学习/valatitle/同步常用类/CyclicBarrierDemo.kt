package 多线程学习.valatitle.同步常用类

import java.util.concurrent.CyclicBarrier
import java.util.concurrent.TimeUnit

object CyclicBarrierDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val threadNum = 5
        val barrier = CyclicBarrier(threadNum, Runnable {
            println("\n ${Thread.currentThread().name} 完成最后任务")
        })
        for (i in 0 until 5) {
            TaskThread(barrier).start()
        }
        Thread.sleep(1000)
        barrier.reset()
    }
    internal class TaskThread(var barrier: CyclicBarrier) : Thread() {
        override fun run() {
            try {
                sleep(1000)
                print("$name 到达栅栏 A \t")
                barrier.await()
                println("$name 冲破栅栏 A")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}