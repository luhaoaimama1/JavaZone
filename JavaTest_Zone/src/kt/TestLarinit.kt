package kt

class TestLarinit {
    lateinit var ab: String
    val z by lazy {
        "ab"
    }

    var callback: ((str: String) -> Unit)? = null

    fun initHaha() {
        ab = "abc"
        ab = "mzc"
    }

    fun destory() {
        callback?.invoke(ab)
    }

    fun request() {
        Thread {
            Thread.sleep(200)
            callback?.invoke(ab)
        }.start()
    }
}

fun main(args: Array<String>) {
    var testLarinit: TestLarinit? = TestLarinit()
    testLarinit?.initHaha()
    testLarinit?.callback = {
        print("hahah")
    }
    testLarinit?.request()
    testLarinit = null
}