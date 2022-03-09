package ktxc

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
//        zuse()
//        noZuse()
        lazy()
    }
}

private suspend fun CoroutineScope.lazy() {
    //惰性的话 可以通过 start 或者await启动 不是直接启动
    val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
    val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
    // 执行一些计算
//        two.start() // 启动第二个
//        one.start() // 启动第一个
    println("The answer is ${two.await() + one.await()}")
}

private suspend fun CoroutineScope.noZuse() {
    //非阻塞的
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    println("The answer is~~~")
    println("The answer is ${one.await() + two.await()}")
}

private suspend fun zuse() {
    //        阻塞
    val one = doSomethingUsefulOne()
    val two = doSomethingUsefulTwo()
    println("The answer is ${one + two}")
}


suspend fun doSomethingUsefulOne(): Int {
    println("doSomethingUsefulOne ")
    delay(1000L) // 假设我们在这里做了一些有用的事
    println("13")
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    println("doSomethingUsefulTwo ")
    delay(1000L) // 假设我们在这里也做了一些有用的事
    println("29")
    return 29
}