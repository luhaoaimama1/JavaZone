package 算法.算法书籍.图

import java.util.*

//todo 连通性 与 union-find
fun main(args: Array<String>) {
    val g = Graph(6)


    g.addEdge(0, 2)
    g.addEdge(0, 5)
    g.addEdge(2, 4)
    g.addEdge(2, 3)
    g.addEdge(1, 2)
    g.addEdge(0, 1)
    g.addEdge(3, 4)
    g.addEdge(3, 5)



    println("深度搜索 【直到走不通，退一步在走。 大概是在找走最长的路】，")
    val depthFirstSearch = DepthFirstSearch(g, 0)
    printStack(depthFirstSearch.getPath(5))
    //打印流程
    depthFirstSearch.node.formatPrint("|------->")

    println("\n 广度搜索【目的获取最短路径】，分裂同时探索 ")
    val bfs = BreadthFirstPaths(g, 0)
    printStack(bfs.getPath(5))
    bfs.nodeArray[0].formatPrint("|------->")

    println("\n 是否有环："+Cycle(g).hasCircle())
    println("\n 是二分图："+TwoColor(g).isTwoColor())

}

private fun printStack(path: Stack<Int>) {
    path.iterator()
    while (!path.isEmpty()) {
        print(path.pop())
    }
    println()
}