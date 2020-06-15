package 算法.算法书籍.图.有向图

fun main(args: Array<String>) {
    val g=Diagraph(6)
    g.addEdge(0,5)
    g.addEdge(5,4)
    g.addEdge(4,3)
    g.addEdge(3,5)

    val directCircle = DirectCircle(g, 0)
    println("是否有环${directCircle.hasCircle() }")
    if(directCircle.hasCircle()){
        println(directCircle.circleStack!!)
    }


    val g2=Diagraph(13)
    g2.addEdge(0,5)
    g2.addEdge(5,4)
    g2.addEdge(0,6)
    g2.addEdge(6,4)
    g2.addEdge(0,1)
    g2.addEdge(2,0)
    g2.addEdge(2,3)
    g2.addEdge(3,5)
    g2.addEdge(8,7)
    g2.addEdge(7,6)
    g2.addEdge(6,9)
    g2.addEdge(9,10)
    g2.addEdge(9,11)
    g2.addEdge(9,12)
    g2.addEdge(11,12)

    println(TopolDFS(g2).topol())
}