package 算法.每日一题;

public class 遍历字符串 {
    static String s = "abcd";

    public static void main(String[] args) {
        //循环长度1-length
        for (int l = 1; l <= s.length(); l++) {
            //开始位置
            for (int start = 0; start <= s.length() - l; start++) {
                System.out.println(s.substring(start, start + l));
            }
        }
    }
}
