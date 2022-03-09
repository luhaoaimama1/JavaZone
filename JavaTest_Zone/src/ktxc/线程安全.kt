package ktxc

import javafx.application.Application.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

val mutex = Mutex()
val lock = Any()
var counter = 0

fun main() {
    runBlocking {
        withContext(Dispatchers.Default) {
            massiveRun {
                // 用锁保护每次自增
                mutex.withLock {
                    counter++
                }
            }
        }
        println("Counter = $counter")
    }

    runBlocking {
        withContext(Dispatchers.Default) {
            massiveRun {
                // 用锁保护每次自增
                synchronized(lock){
                    counter++
                }
            }
        }
        println("Counter222 = $counter")
    }
}

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100  // 启动的协程数量
    val k = 1000 // 每个协程重复执行同一动作的次数
    val time = measureTimeMillis {
        coroutineScope { // 协程的作用域
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}