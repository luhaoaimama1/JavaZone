package 算法.第一章

fun main(args: Array<String>) {
    println("->" + BinarySearch.rank(10, intArrayOf(0, 6, 10, 90, 240)))
}

/**
 *  题目：在有序数组中快速找到一个 整数，并返回他数组中的位置
 */
object BinarySearch {
    fun rank(key: Int, arr: IntArray): Int {
        var lo = 0
        var high = arr.size - 1
        while (lo <= high) {
            val mid = (high - lo) / 2
            if (key < arr[mid]) high = mid - 1
            else if (key > arr[mid]) lo = mid + 1
            else return mid
        }
        return -1
    }
}