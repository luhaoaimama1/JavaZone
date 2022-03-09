package ktxc

import kotlinx.coroutines.*

//https://www.kotlincn.net/docs/reference/coroutines/coroutine-context-and-dispatchers.html
fun main() {
    runBlocking {
//        newSingleThreadContext("Ctx1").use { ctx1 ->
//            newSingleThreadContext("Ctx2").use { ctx2 ->
//                runBlocking(ctx1) {
//                    log("Started in ctx1")
//                    withContext(ctx2) {
//                        log("Working in ctx2")
//                    }
//                    log("Back to ctx1")
//                }
//            }
//        }


        // 启动一个协程来处理某种传入请求（request）
        val request = launch {
            // 孵化了两个子作业, 其中一个通过 GlobalScope 启动
            GlobalScope.launch {
                println("job1: I run in GlobalScope and execute independently!")
                delay(1000)
                println("job1: I am not affected by cancellation of the request")
            }
            // 另一个则承袭了父协程的上下文
            launch {
                delay(100)
                println("job2: I am a child of the request coroutine")
                delay(1000)
                println("job2: I will not execute this line if my parent request is cancelled")
            }
        }
        delay(500)
        request.cancel() // 取消请求（request）的执行
        delay(1000) // 延迟一秒钟来看看发生了什么
        println("main: Who has survived request cancellation?")
    }
}

fun log(str: String) {
    println("${Thread.currentThread().id} : $str")
}
