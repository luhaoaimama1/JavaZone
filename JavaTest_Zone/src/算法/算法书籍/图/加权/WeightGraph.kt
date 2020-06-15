package 算法.算法书籍.图.加权

//无方向
class WeightGraph(val v: Int) {
    val vertexEdgeArray = Array<ArrayList<WeightDiEdge>>(v) { x -> ArrayList<WeightDiEdge>() }
    val edgeArray = ArrayList<WeightDiEdge>()
    private var edgeNumber = 0

    fun addEdge(weightDiEdge: WeightDiEdge): Unit {
        edgeArray.add(weightDiEdge)
        val either = weightDiEdge.either()
        vertexEdgeArray[either].add(weightDiEdge)
        vertexEdgeArray[weightDiEdge.other(either)].add(weightDiEdge)
        edgeNumber++
    }

    fun adj(v: Int) = vertexEdgeArray[v]
    fun e() = edgeNumber
    fun edges() = edgeArray
}