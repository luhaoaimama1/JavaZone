package 算法.算法书籍.排序

import edu.princeton.cs.algs4.*
import java.lang.IllegalStateException

object SortCompare {
    @JvmStatic
    fun main(args: Array<String>) {
//        val arraysCount = 1000
//        val arraysSize = 100
//        //发现数组数量越小 差距越大，数组数量越大 差距越小
//        println("Insertion  $arraysCount 个数组为 $arraysSize 的时间:${timeRandomInput("Insertion", arraysSize, arraysCount)}")
//        println("Selection  $arraysCount 个数组为 $arraysSize 的时间:${timeRandomInput("Selection", arraysSize, arraysCount)}")


        val arraysCount2 = 1000
        val arraysSize2 = 3000 //100的时候 都是insert快  1000就不是了
        println("Insertion  $arraysCount2 个数组为 $arraysSize2 的时间:${timeRandomInput("Insertion", arraysSize2, arraysCount2)}")
        println("Shell  $arraysCount2 个数组为 $arraysSize2 的时间:${timeRandomInput("Shell", arraysSize2, arraysCount2)}")
        println("Quick  $arraysCount2 个数组为 $arraysSize2 的时间:${timeRandomInput("Quick", arraysSize2, arraysCount2)}")
        println("Quick3  $arraysCount2 个数组为 $arraysSize2 的时间:${timeRandomInput("Quick3", arraysSize2, arraysCount2)}")
    }

    /**
     * T个长度为N的数组 用method方法排序
     * @param method
     * @param n
     * @param t
     * @return
     */
    private fun timeRandomInput(method: String, n: Int, t: Int): Double {
        var total = 0.0
        for (i in 0 until t) {
            val a = arrayOfNulls<Double>(n)
            for (j in 0 until n) {
                a[j] = StdRandom.uniform()
            }
            total += time(method, a)
        }
        return total
    }

    fun time(method: String, a: Array<Double?>?): Double {
        val s = Stopwatch()
        when (method) {
            "Selection" -> Selection.sort(a)
            "Insertion" -> Insertion.sort(a)
            "Shell" -> Shell.sort(a)
            "Quick" -> 快速排序.sort(a,0,a!!.size-1);
            "Quick3" -> 快速排序.fastThreeSplit(a,0,a!!.size-1)
            else -> throw  IllegalStateException("aaaa")
        }
        return s.elapsedTime()
    }
}