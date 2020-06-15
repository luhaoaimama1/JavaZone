package 算法.每日一题

object 验证回文串 {
    @JvmStatic
    fun main(args: Array<String>) {
//        println(validPalindrome("A man,   a plan, a canal: Panama"))
//        println(validPalindrome("race a car"))
//        println(validPalindrome(".,"))
        println(validPalindrome("a"))
    }

    fun validPalindrome(s: String): Boolean {
        var l = 0
        var r = s.lastIndex
        while (l < r) {//=代表一定相同啊 所以不需要=
            while (!Character.isJavaLetterOrDigit(s[l]) && l < r) {
                l++ //代表 l最大=r
            }
            while (!Character.isJavaLetterOrDigit(s[r]) && l < r) {
                r-- //代表 r最小=l
            }
            if (s[l].toLowerCase() != s[r].toLowerCase()) return false
            l++
            r--
        }
        return true
    }
}