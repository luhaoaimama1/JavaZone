package 算法.算法书籍.图.最短路径


class EdgeWeightDigraph(val v: Int) {
    private val vertexEdgeArray = Array<ArrayList<WeightDiEdge2>>(v) { x -> ArrayList<WeightDiEdge2>() }
    private val edgeArray = ArrayList<WeightDiEdge2>()
    fun addEdge(e: WeightDiEdge2) {
        vertexEdgeArray[e.from].add(e)
        edgeArray.add(e)
    }

    fun adj(v: Int) = vertexEdgeArray[v]
    fun edges() = edgeArray
    fun e() = edgeArray.size
}