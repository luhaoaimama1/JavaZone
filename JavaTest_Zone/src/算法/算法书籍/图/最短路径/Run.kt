package 算法.算法书籍.图.最短路径

import 算法.算法书籍.图.加权.WeightDiEdge


fun main(args: Array<String>) {
    val g = EdgeWeightDigraph(8)
    g.addEdge(WeightDiEdge2(4, 5, 0.35))
    g.addEdge(WeightDiEdge2(5, 4, 0.35))
    g.addEdge(WeightDiEdge2(4, 7, 0.37))
    g.addEdge(WeightDiEdge2(5, 7, 0.28))
    g.addEdge(WeightDiEdge2(7, 5, 0.28))
    g.addEdge(WeightDiEdge2(5, 1, 0.32))
    g.addEdge(WeightDiEdge2(0, 4, 0.38))
    g.addEdge(WeightDiEdge2(0, 2, 0.26))
    g.addEdge(WeightDiEdge2(7, 3, 0.39))
    g.addEdge(WeightDiEdge2(1, 3, 0.29))
    g.addEdge(WeightDiEdge2(2, 7, 0.34))
    g.addEdge(WeightDiEdge2(6, 2, 0.40))
    g.addEdge(WeightDiEdge2(3, 6, 0.52))
    g.addEdge(WeightDiEdge2(6, 0, 0.58))
    g.addEdge(WeightDiEdge2(6, 4, 0.93))
    val dijkstra = Dijkstra(g, 0)
    val bellman = BellmanForward(g, 0)

    val stack = bellman.pathTo(6)
    while (!stack.isEmpty()) {
        print("${stack.pop()} ,\t")
    }
}