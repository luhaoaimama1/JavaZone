package 算法

fun main(args: Array<String>) {

    val array = intArrayOf(10, -2, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35)
    negativeLeftMove(array)
    for (i in array) {
        print("$i \t ")
    }

}

/**
 *  负数做移动:
 *
 */
private fun negativeLeftMove(array: IntArray) {
    var firstPostiveIndex = 0
    for (i in 0 until array.size) {
        //如果这个a[i]<0 ，负数插到index的位置，刚刚的正数index向右移动
        if (array[i] < 0) {
            val temp = array[i]
            for (j in i downTo firstPostiveIndex + 1) {
                array[j] = array[j - 1]
            }
            array[firstPostiveIndex] = temp
            firstPostiveIndex++
        }
    }

}
