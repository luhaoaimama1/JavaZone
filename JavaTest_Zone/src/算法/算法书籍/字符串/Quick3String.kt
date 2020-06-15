package 算法.算法书籍.字符串

object Quick3String {
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
        sort( arrayOf,0, arrayOf.lastIndex, 0)
        for (s in arrayOf) {
            println(s)
        }
    }

    fun sort(a: Array<String>, lo: Int, hi: Int, k: Int) {
        if (hi <= lo) return
        var lt = lo
        var gt = hi
        val v = charAt(a[lo], k)
        var cursor = lo
        while (cursor <= gt) {
            val t = charAt(a[cursor], k)
            if (t > v) swap(a, cursor, gt--)
            else if (t < v) swap(a, cursor, lt++)
            else cursor++
        }
        sort(a, lo, lt - 1, k)
        if (v > 0) sort(a, lt, gt, k + 1)//代表空字符的话则不需要继续了
        sort(a, gt + 1, hi, k)

    }

    private fun swap(chars: Array<String>, left: Int, right: Int) {
        val temp = chars[right]
        chars[right] = chars[left]
        chars[left] = temp
    }

    private fun charAt(s: String, index: Int) = if (index > s.length - 1) -1 else s.toCharArray()[index].toInt()
}