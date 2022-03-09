package ktshare.second.field_

/**
 * 优点：不会报错了
 * 缺点：大量使用?.的空判断
 */
class NullField {
    var view: View? = null
    fun useAbc() {
        view?.click()
    }

    fun useAbc2() {
        view?.click()
    }
}

fun main(args: Array<String>) {
    NullField().useAbc()
}