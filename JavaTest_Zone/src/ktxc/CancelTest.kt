package ktxc

import kotlinx.coroutines.*
import java.lang.IllegalStateException

suspend fun main() {
    runBlocking {
//        val startTime = System.currentTimeMillis()
//        val job = launch(Dispatchers.Default) {
//            var nextPrintTime = startTime
//            var i = 0
//            while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
//                // 每秒打印消息两次
//                if (System.currentTimeMillis() >= nextPrintTime) {
//                    println("job: I'm sleeping ${i++} ...")
//                    nextPrintTime += 500L
//                }
//            }
//        }
//        delay(1300L) // 等待一段时间
//        println("main: I'm tired of waiting!")
//        job.cancelAndJoin() // 取消一个作业并且等待它结束
//        println("main: Now I can quit.")


//
//        val startTime = System.currentTimeMillis()
//        val job = launch(Dispatchers.Default) {
//            var nextPrintTime = startTime
//            var i = 0
//            while (isActive) { // 可以被取消的计算循环
//                // 每秒打印消息两次
//                if (System.currentTimeMillis() >= nextPrintTime) {
//                    println("job: I'm sleeping ${i++} ...")
//                    nextPrintTime += 500L
//                }
//            }
//        }
//        delay(1300L) // 等待一段时间
//        println("main: I'm tired of waiting!")
//        job.cancelAndJoin() // 取消该作业并等待它结束
//        println("main: Now I can quit.")


//        val job = launch {
//            try {
//                repeat(1000) { i ->
//                    println("job: I'm sleeping $i ...")
//                    delay(500L)
//                }
//            } finally {
//                println("job: I'm running finally")
//            }
//        }
//        delay(1300L) // 延迟一段时间
//        println("main: I'm tired of waiting!")
//        job.cancelAndJoin() // 取消该作业并且等待它结束
//        println("main: Now I can quit.")


//        val job = launch {
//            try {
//                repeat(1000) { i ->
//                    println("job: I'm sleeping $i ...")
//                    delay(500L)
//                }
//            } finally {
//                withContext(NonCancellable) {
//                    println("job: I'm running finally")
//                    delay(1000L)
//                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
//                }
//            }
//        }
//        delay(1300L) // 延迟一段时间
//        println("main: I'm tired of waiting!")
//        job.cancelAndJoin() // 取消该作业并等待它结束
//        println("main: Now I can quit.")


        try {
            withTimeout(1300L) {
                try {
                    repeat(1000) { i ->
                        println("I'm sleeping $i ...")
                        throw IllegalStateException("a !")
                        delay(500L)
                    }
                } finally {
                    println("finally")
                }
            }
        } catch (e: Exception) {
            println("e2:${e}")
        }
    }

}

