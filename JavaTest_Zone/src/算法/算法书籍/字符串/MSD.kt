package 算法.算法书籍.字符串

object MSD {
    @JvmStatic
    fun main(args: Array<String>) {

        val arrayOf = arrayOf(
                "she",
                "sells",
                "seashells",
                "by",
                "the",
                "seashore",
                "the",
                "shells",
                "she",
                "sells",
                "are",
                "surely",
                "seashells"
        )
        val aux = arrayOfNulls<String>(arrayOf.size) as Array<String>
        sort(256, aux, arrayOf, 0, arrayOf.lastIndex, 0)
        for (s in arrayOf) {
            println(s)
        }
    }

    /**
     * 索引计数法
     */
    fun sort(R: Int, aux: Array<String>, a: Array<String>, lo: Int, hi: Int, j: Int) {
        if (lo >= hi) return

        val offset = 1 //因为-1站在了0的位置。所有的要向后偏移
        //最后一个key的话 那么就要+1去存他了 ,字符串超出了返回-1所以数组需要在+1 然后所有的其他key都offset一位
        val countArray = IntArray(R + 1 + offset)

        for (i in lo..hi) {
            val item = a[i]
            countArray[charAt(item, j) + 1 + offset]++
        }

        for (i in 1 until countArray.size) {
            countArray[i] += countArray[i - 1]
        }

        for (i in lo..hi) {
            val s = a[i]
            val key = countArray[charAt(s, j) + offset]++
            aux[key + lo] = s
        }

        for (i in lo..hi) {
            a[i] = aux[i]
        }

        //对下一个字符排序

        //对于-1则不需要排序了
        val start = 1 + offset
        for (i in start..countArray.lastIndex) {
            sort(R, aux, a, lo + countArray[i - 1], lo + countArray[i] - 1, j + 1)
        }
    }

    private fun charAt(s: String, index: Int) = if (index > s.length - 1) -1 else s.toCharArray()[index].toInt()
}