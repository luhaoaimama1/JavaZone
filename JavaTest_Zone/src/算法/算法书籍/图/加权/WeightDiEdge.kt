package 算法.算法书籍.图.加权

class WeightDiEdge(val from: Int, val to: Int, val weight: Int) : Comparable<WeightDiEdge> {

    fun either() = from

    fun other(vertex: Int) = if (vertex == from) from else to

    override fun compareTo(other: WeightDiEdge): Int = weight - other.weight
}