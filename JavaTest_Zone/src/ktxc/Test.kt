package ktxc

class SSS(val a: String)

fun main() {
    var sss: SSS? = SSS("a")
    back(sss)
    sss = null
}

fun back(sss: SSS?) {
    Thread(Runnable{
        println(sss?.a)
    }).start()
}
