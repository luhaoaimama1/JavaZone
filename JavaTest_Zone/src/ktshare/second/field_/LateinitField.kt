package ktshare.second.field_

class LateinitField {
    lateinit var a: View
    fun useAStep() {
        a.click()
    }
}

fun main(args: Array<String>) {

    //错误用法：
    LateinitField().useAStep()
    //正确用法
    val fieldExpand = LateinitField()
    fieldExpand.a = View()

    fieldExpand.useAStep()
}