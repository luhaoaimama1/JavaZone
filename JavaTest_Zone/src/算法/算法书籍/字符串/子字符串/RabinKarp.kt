package 算法.算法书籍.字符串.子字符串

class RabinKarp(val pattern: String) {

    val Q = 997//散列码进制
    val patternHash = hash(pattern, pattern.lastIndex)
    val patternFirstHash = Math.pow(Q.toDouble(), pattern.length.toDouble()).toInt() % Q

    //"f3"意味f是高位。那么(f*Q+3)%Q
    fun hash(str: String, index: Int): Int {
        var h = 0
        for (i in 0..index) {
            h = (h * Q + str[i].toInt()) % Q
        }
        return h
    }

    fun check(content: String, s: Int): Boolean {
        for (i in 0..pattern.lastIndex) {
            if (content[s + i] != pattern[i]) return false
        }
        return true
    }

    fun search(content: String): Int {
        if (content.length < pattern.length) return -1
        var textHash = hash(content, pattern.lastIndex)
        if (textHash == patternHash && check(content, 0)) return 0

        for (i in 1..content.lastIndex - pattern.length) {
            //字符串整体向右移动  先移除左边的字符得到其散列码
            //为什么textHash-移除首位的散列码后要+Q? 因为要保持正数
            textHash = (textHash - (patternFirstHash * content[i - 1].toInt()) % Q + Q) % Q

            //字符串 右边增加一位计算hash
            textHash = (textHash * Q + content[pattern.lastIndex + i].toInt()) % Q

            if (textHash == patternHash && check(content, i)) return i
        }
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(RabinKarp("abc").search("zoneabccab"))
            println(RabinKarp("abc").search("akb"))
        }
    }
}