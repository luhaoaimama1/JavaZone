package 算法.算法书籍.图.加权

import edu.princeton.cs.algs4.MinPQ

class PrimMST(val g: WeightGraph) {

    val hEdgeArray = MinPQ<WeightDiEdge>()
    val minEdgeArray = ArrayList<WeightDiEdge>()
    val marked = BooleanArray(g.v) { false }

    init {
        mst(0)
        while (!hEdgeArray.isEmpty) {
            val min = hEdgeArray.delMin()
            val from = min.either()
            val other = min.other(from)
            if (marked[from] && marked[other]) continue
            minEdgeArray.add(min)
            if (!marked[from]) mst(from)
            if (!marked[other]) mst(other)
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