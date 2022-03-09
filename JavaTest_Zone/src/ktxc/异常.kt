package ktxc

import kotlinx.coroutines.*

suspend fun main() {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    val job = GlobalScope.launch(handler) { // 根协程，运行在 GlobalScope 中
        throw AssertionError()
    }
    val deferred = GlobalScope.async(handler) { // 同样是根协程，但使用 async 代替了 launch
        throw ArithmeticException() // 没有打印任何东西，依赖用户去调用 deferred.await()
    }
    joinAll(job, deferred)
}