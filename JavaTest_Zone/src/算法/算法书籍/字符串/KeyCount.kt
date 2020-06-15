package 算法.算法书籍.字符串

class StringIndex(val index: Int, val str: String)


object KeyCount {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = arrayOf(
                StringIndex(2, "Anderson"),
                StringIndex(3, "Brown"),
                StringIndex(3, "Davis"),
                StringIndex(4, "Garcia"),
                StringIndex(1, "Harris"),
                StringIndex(3, "Jackson"),
                StringIndex(4, "Johnson"),
                StringIndex(3, "Jones"),
                StringIndex(1, "Martin"),
                StringIndex(2, "Martinez"),
                StringIndex(2, "Miller"),
                StringIndex(1, "Moore"),
                StringIndex(2, "Robinson"),
                StringIndex(4, "Smith"),
                StringIndex(3, "Taylor"),
                StringIndex(4, "Thomas"),
                StringIndex(4, "Thompson"),
                StringIndex(2, "White"),
                StringIndex(3, "Williams"),
                StringIndex(4, "Willson")
        )
        val aux = arrayOfNulls<StringIndex>(array.size) as Array<StringIndex>
        val countArray = IntArray(6) { 0 }

        //统计key组的个数
        for (stringIndex in array) {
            countArray[stringIndex.index + 1]++
        }

        //然后根据key组的个数，对应数组的位置。  规则就是keyN=key(n-1)+k(n)的个数
        for (i in 1 until countArray.size) {
            countArray[i] += countArray[i - 1]
        }

        for (i in 0..aux.lastIndex) {
            val key = countArray[array[i].index]++
            aux[key] = array[i]
        }
        //复刻
        for (i in 0..aux.lastIndex) {
            array[i] = aux[i]
            println("index:${array[i].index}  string:${array[i].str}")
        }


    }

    /**
     * 索引计数法
     */
    fun keyCount(R: Int, a: Array<String>, j: Int, aux: Array<String>) {
        //最后一个key的话 那么就要+1去存他了
        val countArray = IntArray(R + 1) { 0 }

        for (item in a) {
            countArray[item.toCharArray()[j].toInt() + 1]++
        }

        for (i in 1 until countArray.size) {
            countArray[i] += countArray[i - 1]
        }

        for (i in 0..aux.lastIndex) {
            val s = a[i]
            val key = countArray[s.toCharArray()[j].toInt()]++
            aux[key] = s
        }

        for (i in 0..aux.lastIndex) {
            a[i] = aux[i]
        }
    }
}
