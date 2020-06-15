package 算法.算法书籍.字符串

object LSD {
    @JvmStatic
    fun main(args: Array<String>) {
        val arrayOf = arrayOf(
                "4PGC938",
                "2IYE230",
                "3CI0720",
                "1ICK750",
                "1OHV845",
                "4JZY524",
                "1ICK750",
                "3CI0720",
                "1OHV845",
                "1OHV845",
                "2RLA629",
                "3ATW723"
        )
        sort(arrayOf, 7)
        for (s in arrayOf) {
            println(s)
        }
    }

    /**
     * w个字符 定长
     */
    fun sort(a: Array<String>, w: Int) {

        val R = 256
        val n = a.size

        val aux = arrayOfNulls<String>(n) as Array<String>

        for (j in w - 1 downTo 0) {
            //如果不在这每次都声明的话 ，会有缓存的
            KeyCount.keyCount(R, a, j, aux)
        }
    }


}