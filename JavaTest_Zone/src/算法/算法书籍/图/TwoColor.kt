package 算法.算法书籍.图

/**
 *
 * 检查所有连通分量，在检查一个连通分量的时候标记去放置颜色，放置的颜色与父节点相反，
 * 如果检查下一个节点已经被标记了但是和当前颜色一致那么代表不是二色图
 */
class TwoColor(g: Graph) {
    protected val marked = arrayOfNulls<Boolean>(g.vertex) as Array<Boolean>
    protected val colors =arrayOfNulls<Boolean>(g.vertex) as Array<Boolean>

    init {
        for (i in 0 until g.vertex) {
            marked[i] = false
            colors[i] = false
        }
        for (i in 0 until g.vertex) {
            if (!marked[i]) {
                dfs(g, i)
            }
        }
    }
    private var isTwoColor=true
    //对于第一个节点他有默认的颜色是false
    fun dfs(g: Graph, v: Int) {
        marked[v] = true
        val adj = g.adj(v)
        for (i in 0 until adj.size) {
            val nextNot = adj[i]
            if (!marked[nextNot]) {
                marked[nextNot]=!marked[v]
                dfs(g, nextNot)
            } else if (marked[nextNot]==marked[v]) { //如果下一个节点已经被标记了但是和当前颜色一致那么代表不是二色图
                isTwoColor = false
                return
            }
        }
    }

    fun isTwoColor() = isTwoColor
}