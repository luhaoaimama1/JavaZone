package 算法.算法书籍.图

import 算法.算法书籍.工具.IteratorNode
import java.awt.Paint
import java.util.*
import kotlin.collections.HashMap

/**
 * 连通分量。深度和广度搜索都是可以找到一段从某个点开始的连通分量的
 *
 * 所以循环 所有的点 对这个点进行深度搜索。
 * 如果这个点已经被以前的深度搜索标记过。那么跳过这个点。如果没有那么就是新的片段 count++
 */
class DepthFirstSearchUF(g: Graph, s: Int) : DepthFirstSearch(g, s) {
    private val ids = arrayOfNulls<Int>(g.vertex) as Array<Int>
    private var count = 0

    init {
        for (i in 0 until g.vertex) {
            if (!marked[i]) {
                dfs(g, i, node)
                count++ //:count 从0开始
            }
        }
    }

    override fun initUseDfs(g: Graph) {
//        super.initUseDfs(g)
    }

    override fun dfs(g: Graph, v: Int, node: IteratorNode<Pair<Int, Boolean>>) {
        ids[v] = count
        super.dfs(g, v, node)
    }

    fun count() = count

    fun connect(v: Int, w: Int) = ids[v] == ids[w]

    fun id(v: Int) = ids[v]
}