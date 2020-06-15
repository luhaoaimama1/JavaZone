package 算法.算法书籍.字符串.子字符串

object StringSearch {
    fun search(pattern: String, content: String): Int {
        //-1的原因是因为当前字符 就包括一位了
        for (i in 0..(content.lastIndex - (pattern.length - 1))) {
            var j = 0
            while (j <= pattern.lastIndex) {
                if (content[i + j] != pattern[j]) break
                else j++
            }
            if (j == pattern.length) return i
        }
        return -1
    }

    //显式回退
    fun search2(pattern: String, content: String): Int {
        var cursor = 0
        var j = 0
        while (cursor < content.length && j < pattern.length) {
            if (content[cursor] == pattern[j]) j++
            else {
                cursor -= j //回退到开配的开始
                j = 0
            }
            cursor++
        }
        return if (j == pattern.length) cursor
        else -1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(search("abc", "zoneabccab"))
        println(search("abc", "akb"))
    }
}