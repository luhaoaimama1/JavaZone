package 算法.算法书籍.字符串.子字符串


class BoyerMoore(val pattern: String) {
    val R = 256
    //跳跃表
    val right = arrayOfNulls<Int>(R) as Array<Int>

    init {
        //为什么默认值是-1？因为 这样(M-1)-(-1)才能等于M 不然后边还需要+1
        for (i in 0 until R) {
            right[i] = -1
        }

        //如果有重复的话就会覆盖，那么代表该字符离右侧最近的值
        for (i in 0..pattern.lastIndex) {
            right[pattern[i].toInt()] = i
        }
    }

    fun search(content: String): Int {

        var cursor = pattern.lastIndex
        while (cursor <= content.lastIndex) {
            var skip = 0
            //从右向左匹配
            for (j in pattern.lastIndex downTo 0) {
                val c = content[cursor - (pattern.lastIndex - j)] //最大是cursor 最小 cursor-pattern.lastIndex
                if (pattern[j] != c) {
                    skip = j - right[c.toInt()]
                    if (skip < 1) skip = 1 //如果那个字符在右侧的话可能是负的值 所以如果出现不同 我们让他最少跳跃一位
                    break
                }
            }
            if (skip == 0) return cursor - pattern.lastIndex //匹配成功
            cursor += skip
        }
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(BoyerMoore("abc").search("zoneabccab"))
            println(BoyerMoore("abc").search("akb"))
        }
    }
}