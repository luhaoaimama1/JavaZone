package 算法.算法书籍.图.最短路径

import java.util.*

//计算图g中 距离s 最短距离的所有点
//非负 无环
class Dijkstra(val g: EdgeWeightDigraph, val s: Int) {

    private val edgeTo = arrayOfNulls<WeightDiEdge2>(g.v) as Array<WeightDiEdge2>
    private val distTo = DoubleArray(g.v) { Double.MAX_VALUE } //距离起点s点 所有点距离的数组
    private val queueEdge = LinkedList<WeightDiEdge2>()
    fun relax(e: WeightDiEdge2) {
        val oldDist = distTo[e.to]
        val newDist = distTo[e.from] + e.weight
        if (distTo[e.from]!= Double.MAX_VALUE&&oldDist > newDist) { //最小距离
            distTo[e.to] = newDist
            edgeTo[e.to] = e
            queueEdge.add(e) //添加边
        }
    }

    fun relax(v: Int) {
        for (WeightDiEdge2 in g.adj(v)) {
            relax(WeightDiEdge2)
        }
    }

    init {
        distTo[s] = 0.0
        relax(s) //松弛起点 ->添加松弛后得到边，然后取出所有的边，松弛边上to的点。直到队列取不出
        //这里面要注意的一个问题 就是新的点是强连通怎么办呢？to的边E。又指回自己怎么办。如果把边填进去 那么岂不是无限循环了？
        // 但是这种不会发生。因为添加边到序列的方法 需要是最小距离。如果已经有最小距离了。那么第二次就不会比那个小 所以就不会添加。而且距离默认是double.Max。to.
        while (!queueEdge.isEmpty()) {
            relax(queueEdge.pop().to)
        }
    }

    //v到s的最短距离
    fun distTo(v: Int) = distTo[v]

    fun hashPathTo(v: Int) = distTo[v] != Double.MAX_VALUE

    fun pathTo(v: Int) = kotlin.run {
        val stack = Stack<WeightDiEdge2>()
        var cursor = v
        while (cursor != s) {
            val edge = edgeTo[cursor]
            stack.add(edge)
            cursor = edge.from
        }
        stack
    }
}