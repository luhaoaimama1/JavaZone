package ktxc

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
//        launch { // 运行在父协程的上下文中，即 runBlocking 主协程
//            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
//        }
//        launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
//            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
//        }
////        所以 launch(Dispatchers.Default) { …… } 与 GlobalScope.launch { …… } 使用相同的调度器。
//        launch(Dispatchers.Default) { // 将会获取默认调度器
//            println("Default               : I'm working in thread ${Thread.currentThread().name}")
//        }
//        launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
//            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//        }
//

        launch(Dispatchers.Unconfined) { // 非受限的——将和主线程一起工作
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
        launch { // 父协程的上下文，主 runBlocking 协程
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
    }
}