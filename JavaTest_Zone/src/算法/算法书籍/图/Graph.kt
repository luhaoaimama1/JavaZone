package 算法.算法书籍.图

import kt.扩展属性包外使用方式.fieldTest

class Graph(val vertex: Int) {


    private var edges = 0
    private var array: Array<ArrayList<Int>> = arrayOfNulls<ArrayList<Int>>(vertex) as Array<ArrayList<Int>>

    init {
        array.forEachIndexed { index, _ ->
            array[index] = ArrayList()
        }
    }

    fun addEdge(v: Int, w: Int) {
        array[v].add(w)
        array[w].add(v)
        edges++
    }

    fun adj(v: Int) = array[v]
    fun getEdges() = edges


}
