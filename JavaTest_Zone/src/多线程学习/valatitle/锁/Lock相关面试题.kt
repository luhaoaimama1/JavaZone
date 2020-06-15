package 多线程学习.valatitle.锁

import java.util.concurrent.locks.ReentrantLock

// 需求:
//* 子线程循环输出10次
//* 接着主线程循环输出20次
//* 接着又回到子线程执行10次
//* 再回到主线程执行20次
//* 如此往复5次 请写出程序
//* 此处使用阻塞队列来实现

//android handlerThread 与主线程通信 就好实现了 java的话
object Lock相关面试题 {
    var count = 1

    @JvmStatic
    fun main(args: Array<String>) {
        val lock = ReentrantLock()
        val thread = lock.newCondition()
        val main = lock.newCondition()
        Thread {
            try {
                lock.lock()
                while (count < 5) {
                    print("Thread==>")
                    for (i in 0..10) {
                        print("$i , \t")
                    }
                    println()
                    main.signal()
                    thread.await()
                }
            } finally {
                lock.unlock()
            }
        }.start()


        try {
            lock.lock()
            while (count < 5) {
                main.await()
                print("Main==>")
                for (i in 0..20) {
                    print("$i , \t")
                }
                count++
                println()
                thread.signal()
            }
        } finally {
            lock.unlock()
        }
    }
}