package ktxc

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking { // this: CoroutineScope
        launch {
            delay(200L)
            println("${Thread.currentThread().id}  Task from runBlocking")
        }

        coroutineScope { // 创建一个协程作用域
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
        }

        println("${Thread.currentThread().id}  Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
    }
}