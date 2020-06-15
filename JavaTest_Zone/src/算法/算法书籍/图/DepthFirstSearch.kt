package 算法.算法书籍.图

import 算法.算法书籍.工具.IteratorNode
import java.awt.Paint
import java.util.*
import kotlin.collections.HashMap

/**
 *
 * 【直到走不通，退一步在走。mark过的就不走 大概是在找走最长的路】
 *
 * 一个人探路 ,自己走过的路会留下标记，当碰到标记后 就返回到上一步继续走
 * 直到走到。所有点都被探测过。 会得到一个最长的路径.
 *
 * 真正的流程举例：
 * 递归当前点s所有可能的点。然后挨个走 碰到已经标记过的点 则就不走这步了。例如a,b,c.
 * 走到a的时候继续递归。结果就是如下
 * 递归a，例如a1,a2,a3
 *      递归a1 例如a11,a12,a13
 *              递归a11 ...
 *               ..
 *      递归a2
 *      递归a3
 *      ..
 * 递归b
 * 递归c
 *
 *
 * 如何得到路径？
 * 但是顺序去取的话，每个点的下一步可能有多条
 * 但是逆序取的，每个点对应的上一个点只有一个。
 * 那么根据最后一个点存进去后逆序取即可。
 */
open class DepthFirstSearch(g: Graph, val s: Int) {
    protected val marked = arrayOfNulls<Boolean>(g.vertex) as Array<Boolean>

    protected val path = arrayOfNulls<Int>(g.vertex) as Array<Int>
    val node: IteratorNode<Pair<Int, Boolean>>

    init {
        for (i in 0 until g.vertex) {
            marked[i] = false
        }
        node = object : IteratorNode<Pair<Int, Boolean>>(Pair(s, true)) {
            override fun formatString(node2: IteratorNode<Pair<Int, Boolean>>): String {
                val string = if (!node2.value.second) "【X】" else ""
                return "${node2.value.first}$string"
            }
        }
        initUseDfs(g)
    }

    open fun initUseDfs(g: Graph) {
        dfs(g, s, node)
    }

    open fun dfs(g: Graph, v: Int, node: IteratorNode<Pair<Int, Boolean>>) {
        marked[v] = true
        val adj = g.adj(v)
        for (i in 0 until adj.size) {
            val w = adj[i]
            if (!marked(w)) {
                path[w] = v
                val element1 = IteratorNode(Pair(w, true))
                node.arraylist.add(element1)
                dfs(g, w, element1)
            } else {
                node.arraylist.add(IteratorNode(Pair(w, false)))
            }
        }
    }

    fun marked(v: Int) = marked[v]

    fun getPath(v: Int) = kotlin.run {
        val stack = Stack<Int>()
        var cursorKey = v
        //到起点就停了
        while (cursorKey != s) {
            stack.add(cursorKey)
            cursorKey = path[cursorKey]
        }
        stack.add(s)
        stack
    }
}