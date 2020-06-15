package 算法.算法书籍.图.有向图


class DirectDFS {
    val g: Diagraph
    val marked: BooleanArray

    constructor(g: Diagraph, s: Int) {
        this.g = g
        marked = BooleanArray(g.v) { false }
        dfs(s)
    }

    constructor(g: Diagraph, s: List<Int>) {
        this.g = g
        marked = BooleanArray(g.v) { false }
        for (i in s) {
            if (!marked(i)) dfs(i)
        }
    }


    fun dfs(s: Int) {
        marked[s] = true
        for (nextV in g.adj(s)) {
            if (!marked[nextV]) {
                dfs(nextV)
            }
        }
    }

    fun marked(v: Int) = marked[v]
}