package 算法.算法书籍.图.有向图

import java.util.*

class TopolDFS(val g: Diagraph) {
    private var marked = BooleanArray(g.v) { false }
    private var topolStack = Stack<Int>()

    init {
        for (i in 0 until g.v) {
            if (!marked[i]) {
                dfs(i)
            }
        }
    }

    fun dfs(v: Int) {
        marked[v] = true
        for (nextV in g.adj(v)) {
            if (!marked[nextV]) {
                dfs(nextV)
            }
        }
        topolStack.push(v)
    }

    fun marked(v: Int) = marked[v]
    fun topol() = topolStack
}