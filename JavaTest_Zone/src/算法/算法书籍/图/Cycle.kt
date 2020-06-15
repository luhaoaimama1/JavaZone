package 算法.算法书籍.图

/**
 * 如何判断是否有环，
 *  当前点与根节点连通。 当前点检查下一个点如果也是与根节点连通的 但确不是他的父节点的过 那么就证明有环
 *
 *  对所有的点都检测一下上面的流程
 */
class Cycle(g: Graph) {
    protected val marked = (arrayOfNulls<Boolean>(g.vertex) as Array<Boolean>).apply {
        for (i in 0 until g.vertex) {
            this[i] = false
        }
    }

    protected val path = arrayOfNulls<Int>(g.vertex) as Array<Int>
    private var hasCircle = false

    init {
        for (i in 0 until g.vertex) {
            if (!marked[i]) {
                dfs(g, i, i)
            }
        }
    }

    fun dfs(g: Graph, v: Int, p: Int) {
        marked[v] = true
        val adj = g.adj(v)
        for (i in 0 until adj.size) {
            val nextNot = adj[i]
            if (!marked[nextNot]) {
                path[nextNot] = v
                dfs(g, nextNot, v)
            } else if (nextNot != p) {
                hasCircle = true
                return
            }
        }
    }

    fun hasCircle() = hasCircle
}