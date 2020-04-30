package 算法.每日一题;

import java.util.HashMap;

//https://leetcode-cn.com/problems/longest-palindromic-substring/
class 最长回文子串 {
    public static void main(String[] args) {
//        String a = longestPalindrome("a");
//        System.out.println(isPN("adada"));
        System.out.println(longestPalindrome2("cbbd"));
    }


    /**
     * 切刀平移的方法例如“ ambab” 的结果是bab 那么切刀的位置在a那么切刀的left与right同时反方向移动一位，
     * 结果相同的话记录字符串与result的对比长度。如果不同的话切刀就向右移动  额外注意的是 "abbc"这种 切刀在字符中间的处理
     *
     * @param s
     * @return
     */
    static public String longestPalindrome2(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            int len1 = split(s, i, i);
            int len2 = split(s, i, i + 1);
            int realLen = Math.max(len2, len1);
            if (realLen > result.length()) {
                //"cbbc"  len:2 i=1 那么begin应该也是1 所以 2的时候位移是0
                //"aba"  len:3 i=1 那么begin应该也是0 所以 3的时候位移是1
                //i-位移（2=0，3=1的话 推出 (realLean-1)/2）
                int beginIndex = i - (realLen - 1) / 2;
                result = s.substring(beginIndex,i+realLen/2+1);
            }
        }
        return result;
    }

    /**
     * @return 返回长度
     */
    private static int split(String s, int i, int j) {
        int left = i, right = j;
        //数组越界 则该切刀 结束
        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return (right + 1 - left) - 2;
    }


    //暴力法
    static public String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                if (isPN(substring) && substring.length() > result.length()) {
                    result = substring;
                }
            }
        }
        return result;
    }

    private static boolean isPN(String result) {
        for (int i = 0; i < result.length() / 2; i++) {

            if (result.charAt(i) != result.charAt(result.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }


}