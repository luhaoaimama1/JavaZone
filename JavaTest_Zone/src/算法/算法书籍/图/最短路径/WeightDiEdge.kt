package 算法.算法书籍.图.最短路径

class WeightDiEdge2(val from: Int, val to: Int, val weight: Double){
    override fun toString(): String {
        return String.format("%d -> %d",from,to)
    }
}