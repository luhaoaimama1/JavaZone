package 算法.算法书籍.图.加权

import edu.princeton.cs.algs4.MinPQ
import edu.princeton.cs.algs4.UF

class KruskalMST(val g: WeightGraph) {

    val hEdgeArray = MinPQ<WeightDiEdge>()
    val minEdgeArray = ArrayList<WeightDiEdge>()
    val marked = BooleanArray(g.v) { false }

    init {
        for (edge in g.edges()) {
            hEdgeArray.insert(edge)
        }

        val uf = UF(g.v)
        while (!hEdgeArray.isEmpty && minEdgeArray.size == g.v - 1) {
            val e = hEdgeArray.delMin()
            val vFrom = e.either()
            val vTo = e.other(vFrom)

            if (uf.connected(vFrom, vTo)) continue //如果连通 说明这连个点已经有在最小生成树中了 忽略

            uf.union(vFrom, vTo)
            minEdgeArray.add(e)
        }
    }

    fun mst(v: Int) {
        marked[v] = true
        for (edge in g.adj(v)) {
            if (!marked[edge.other(v)]) {
                hEdgeArray.insert(edge)
            }
        }
    }

}