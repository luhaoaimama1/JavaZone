package 算法.算法书籍.工具

import java.lang.StringBuilder


open class IteratorNode<T>(var value: T) {
    val arraylist by lazy { ArrayList<IteratorNode<T>>() }

    fun formatPrint(connectString: String) {
        println()
        iteratorPrint(this, connectString, false, recursionIndex)
    }

    var recursionIndex = RecursionIndex()

    fun iteratorPrint(node: IteratorNode<T>, connectString: String, isEnter: Boolean, recursionIndex: RecursionIndex) {

        val intLength = 5
        val format = String.format("%-${intLength}s", formatString(node))
        val sb = StringBuilder()
        val s = if (isEnter) "\n" else ""
        sb.append(s)
        if (isEnter) {
            val spaceNum = Math.max(recursionIndex.backIndex, 0) * intLength + Math.max(recursionIndex.backIndex - 1, 0) * connectString.length
            for (i in 0 until spaceNum) {
                sb.append(" ")
            }
        }
        if (recursionIndex.backIndex != 0) sb.append(connectString)
        sb.append(format)
        print("$sb")
        node.arraylist.forEachIndexed { index, iteratorNode ->
            iteratorPrint(iteratorNode, connectString, if (index == 0) false else true, recursionIndex.apply { backIndex++ })
        }
        recursionIndex.backIndex--
    }

    open fun formatString(node: IteratorNode<T>) = node.value.toString()


    //用Integer不行 貌似自动装箱的问题
    class RecursionIndex {
        var backIndex = 0
    }
}