package ktxc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun main() {

    val launch = GlobalScope.launch {
        val channel = Channel<Int>()
        launch {
            // 这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做 5 次整数的平方并发送
            for (x in 1..5) channel.send(x * x)
        }
// 这里我们打印了 5 次被接收的整数：
        repeat(5) { println(channel.receive()) }
        println("Done!")
    }
    launch.join()
}