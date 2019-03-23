package kt.强制刷新问题

/**
 * Created by fuzhipeng on 2018/11/28.
 */

fun main(args: Array<String>) {
    for (i in 0..2) {
        method1(i,i+1)
    }

}

private fun method1(firstValue:Int,secondValue:Int) {
    var a = firstValue
    GO().go(object : Callback {
        var tempA = a
        override fun doWhat() {
            println("a:$a======tempA:$tempA")
        }
    })
    a = secondValue
}

interface Callback {
    fun doWhat()
}

class GO {
    fun go(callback: Callback?) {
        Thread(Runnable {
            Thread.sleep(1000)
            callback?.doWhat()
        }).start()
    }

}