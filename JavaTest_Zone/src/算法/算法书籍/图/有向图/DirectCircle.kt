package 算法.算法书籍.图.有向图

import java.util.*

class DirectCircle(val g: Diagraph, val s: Int) {
    private var marked = BooleanArray(g.v) { false }
    private var onStack = BooleanArray(g.v) { false }
    private var pFrom = IntArray(g.v) { s }

    var circleStack: Stack<Int>? = null

    init {
        dfs(s)
    }

    fun dfs(s: Int) {
        marked[s] = true
        onStack[s] = true

        for (nextV in g.adj(s)) {
            if (!marked[nextV]) {
                pFrom[nextV] = s
                dfs(nextV)
            } else if (onStack[s]) {
                circleStack = Stack()
                var cursor = s
                while (cursor != nextV) {
                    circleStack?.push(cursor)
                    cursor = pFrom[cursor]
                }
                circleStack?.push(nextV)
                circleStack?.push(s)
            }
        }
        onStack[s] = false
    }

    fun marked(v: Int) = marked[v]

    fun hasCircle() = circleStack != null
    fun circleStack() = circleStack

}