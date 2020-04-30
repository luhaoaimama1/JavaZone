package 算法.每日一题;

import java.util.HashMap;
import java.util.HashSet;
//https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
class 无重复字符的最长子串 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abba"));
    }
    //滑动窗口
    static public int lengthOfLongestSubstring2(String s) {
        //存取上一个相同字符的位置
        HashMap<Character, Integer> map = new HashMap<>();
        int result = 0;
        //滑动窗口
        // substring[i,j] 如果s[j+1]在刚刚的窗口里有重复的 那么，i需要定位到 重复之后的位置这样字符串就不重复了
        for (int i = 0, j = 0; j < s.length(); j++) {
            char o = s.charAt(j);
            if (map.containsKey(o)) {
                Integer integer = map.get(o);
                i = Math.max(integer + 1, i);//代表只能左边的值只能向右滑动
                //滑动过后  也是单独的一个字符串 所以也要计算长度
            }
            result = Math.max(j + 1 - i,result);
            map.put(o, j);
        }
        return result;
    }

    //暴力法 超出时间限制
    static public int lengthOfLongestSubstring(String s) {
        //暴力循环出所有字符串  判断子字符串是否有重复 如果没有得到长度
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                result = Math.max(result, isUniqueString(substring));
            }
        }
        return result;
    }

    private static int isUniqueString(String substring) {
        HashSet set = new HashSet();
        for (int i = 0; i < substring.length(); i++) {
            char o = substring.charAt(i);
            if (set.contains(o)) {
                return 0;
            } else {
                set.add(o);
            }
        }
        return substring.length();
    }
}