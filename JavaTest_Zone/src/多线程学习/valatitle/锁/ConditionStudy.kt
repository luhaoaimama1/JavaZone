package 多线程学习.valatitle.锁

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

//想使用con 必须使用lock lock住
object ConditionStudy {

    @JvmStatic
    fun main(args: Array<String>) {
        val lock = ReentrantLock()
        val con = lock.newCondition()  //注意要使用con必须是lock状态
        Thread {
            try {
                lock.lock()
                println("Thread111 等待中 islocked:  ${lock.isLocked}}")
                con.await() //lock状态变成unlock状态 代码堵塞ing 死循环（也可以叫做自旋）
//                val time=con.awaitNanos(TimeUnit.SECONDS.toNanos(3))
//                println("Thread111  唤醒成功 time${time}")
                //被唤醒后  又变成有锁状态 finally解锁
                println("Thread111  唤醒成功")
            } finally {
                lock.unlock()
            }
        }.start()

        Thread {
            try {
                lock.lock()
                println("Thread3333  等待中")
                Thread.sleep(3000)
                //一个对应一个wait 如果有两个wait则需要两个 或者signalAll
                con.signal() //注意 sigal并不会向await一样 unlock锁
            } finally {
                println("Thread3333  等待中 解锁")
                Thread.sleep(3000)
                println("Thread3333  解锁")
                lock.unlock()
            }
        }.start()
    }
}