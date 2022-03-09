package ktxc

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

internal object MyScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Default
//        get() = Dispatchers.Default
}

class Activity {
    private val mainScope = MyScope

    //    MainScope()
    fun destroy() {
        mainScope.cancel()
        println("destroy:${mainScope.isActive}")

    }

    fun doSomething() {
        // 在示例中启动了 10 个协程，且每个都工作了不同的时长
        repeat(10) { i ->
            mainScope.launch {
                delay((i + 1) * 200L) // 延迟 200 毫秒、400 毫秒、600 毫秒等等不同的时间
                println("Coroutine $i is done isActive:$isActive")
            }
        }
    }
// 继续运行……
}

suspend fun main() {
    val activity = Activity()
    activity.doSomething() // 运行测试函数
    println("Launched coroutines")
    delay(500L) // 延迟半秒钟
    println("Destroying activity!")
    activity.destroy() // 取消所有的协程
//        delay(1000) // 为了在视觉上确认它们没有工作
    Thread.sleep(50000)
}