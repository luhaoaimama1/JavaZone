package 多线程学习.valatitle.锁

import java.util.concurrent.locks.ReentrantReadWriteLock



object Lock相关面试题2 {
    var count = 1

    @JvmStatic
    fun main(args: Array<String>) {
        val lock = ReentrantReadWriteLock()
        val writeLock = lock.writeLock()
        println("writeLock lock")
        writeLock.lock()
        for (j in 0..4) {
            Thread(Runnable{
                val readLock = lock.readLock()

                try {
                    println("${Thread.currentThread().name}==>readLock:"+readLock)
                    readLock.lock()
                        for (i in 0..10) {
                            Thread.sleep(100)
                            println("${Thread.currentThread().name}==>$i , \t")
                        }
                } finally {
                    readLock.unlock()
                }
            },"$j").start()
        }

        Thread.sleep(1000)
        println("writeLock unlock")
        writeLock.unlock()
    }

}