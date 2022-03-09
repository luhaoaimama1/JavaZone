package ktxc

import kotlinx.coroutines.*

suspend fun main() {
    // 启动一个协程来处理某种传入请求（request）
    val request = GlobalScope.launch {
        repeat(3) { i -> // 启动少量的子作业
            launch  {
                delay((i + 1) * 200L) // 延迟 200 毫秒、400 毫秒、600 毫秒的时间
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
    request.join() // 等待请求的完成，包括其所有子协程
    println("Now processing of the request is complete")

    runBlocking {
        async(Dispatchers.Default + CoroutineName("test") + CoroutineName("test33")){
            println("I'm working in thread ${Thread.currentThread().name}")
        }
    }

}