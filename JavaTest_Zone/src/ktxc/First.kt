package ktxc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main(args: Array<String>) {
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//        println("${Thread.currentThread().id}  World!") // 在延迟后打印输出
//    }
//    println("${Thread.currentThread().id}  Hello,") // 协程已在等待时主线程还在继续
//    Thread.sleep(2000000L) // 阻塞主线程 2 秒钟来保证 JVM 存活

//
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//        println("${Thread.currentThread().id}  World!") // 在延迟后打印输出
//    }
//    println("${Thread.currentThread().id}  Hello,") // 协程已在等待时主线程还在继续
//    runBlocking {     // 但是这个表达式阻塞了主线程
//        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
//    }

    //优化点 不需要时间
    val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // 等待直到子协程执行结束
    println("last")
}