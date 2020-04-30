package 算法.每日一题
//https://leetcode-cn.com/problems/string-to-integer-atoi/
fun main() {
//    println(myAtoi("42"))
//    println(myAtoi("   -42"))
//    println(myAtoi("4193 with words"))
//    println(myAtoi("words and 987"))
//    println(myAtoi("-91283472332"))
//    println(myAtoi("+1"))
//    println(myAtoi("   +0 123"))
//    println(myAtoi("2147483646"))
    println(myAtoi("-2147483649"))
    println(myAtoi("-   234"))  //0
    println(myAtoi("0-1")) //0
}

fun myAtoi(str: String): Int {
    var sum = 0
    var symbol = 1
    var symbolCount = 0
    var symbolInit = false
    loop@ for (i in 0 until str.length) {
        when (val c = str[i]) {
            ' ' -> {
                //已经计算的话 碰到这个就不计算了
                if (symbolInit) return sum
                else continue@loop // 空格跳过
            }
            '+', '-' -> {
                if (!symbolInit) symbolInit = true
                else return sum
                symbol = if (c == '-') -1 else 1
            }
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                if (!symbolInit) symbolInit = true
                val right = c.toString().toInt() * symbol

                val overIntMax = isOverIntMax(sum, right)
                if (overIntMax != 0) return overIntMax
                sum = sum * 10 + right
            }
            else -> { //其他字符 终止
                break@loop
            }
        }
    }
    return sum
}

//left：代表 未*10的左边的数  right个位数
fun isOverIntMax(left: Int, right: Int): Int {
    if (left > Int.MAX_VALUE / 10 || (left == Int.MAX_VALUE / 10 && right > Int.MAX_VALUE % 10)) return Int.MAX_VALUE
    if (left < Int.MIN_VALUE / 10 || (left == Int.MIN_VALUE / 10 && right < Int.MIN_VALUE % 10)) return Int.MIN_VALUE
    return 0
}