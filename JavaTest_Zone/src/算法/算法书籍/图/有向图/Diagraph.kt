package 算法.算法书籍.图.有向图

class Diagraph(val v: Int) {
    var vEdges = arrayOfNulls<ArrayList<Int>>(v) as Array<ArrayList<Int>>

    var edgeNum = 0

    init {
        for (i in 0 until v) {
            vEdges[i] = ArrayList()
        }
    }

    fun addEdge(from: Int, to: Int) {
        vEdges[from].add(to)
        edgeNum++
    }

    fun adj(v: Int) = vEdges[v]

    fun reverse() = kotlin.run {
        val reverse = Diagraph(v)
        for (i in 0 until v) {
            for (j in adj(i)) {
                reverse.addEdge(j, i)
            }
        }
        reverse
    }
}