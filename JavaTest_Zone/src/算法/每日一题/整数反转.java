package 算法.每日一题;

import java.util.Arrays;
import java.util.List;
//https://leetcode-cn.com/problems/reverse-integer/
class 整数反转 {
    public static void main(String[] args) {
        System.out.println(reverse2(1534236469));
    }


    /**
     * x%10的话 得到的是个位数，  x/10的话 就是小数点 向左移一位
     * *10的话就是小数点向右移动一位
     *
     * @param x
     * @return
     */
    public static int reverse2(int x) {
        int result = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;//个位数求出后 小数点向左移动一位 把求出的这个移没了
//            result*10+pop >Integer.MAX_VALUE
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10))
                return 0;
//            result*10+pop <Integer.MIN_VALUE
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10))
                return 0;
            result = result * 10 + pop;//小数点向右移动 空出个位数给刚刚计算的值
        }
        return result;
    }


    public static int reverse(int x) {

        Integer[] arrays = new Integer[32];
        int n = 0;
        int absX = Math.abs(x);
        while (absX >= Math.pow(10, n)) {
            int modNumber = (int) Math.pow(10, n + 1);
            int divisionNumber = (int) Math.pow(10, n);
            arrays[n] = (x % modNumber) / divisionNumber;
            n++;
        }

        long sum = 0;
        int index = 0;
        for (int i = arrays.length - 1; i >= 0; i--) {
            if (arrays[i] == null) continue;
            int multiNubmer = (int) Math.pow(10, index);
            sum += arrays[i] * 1L * multiNubmer;
            index++;
        }

        if (sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) sum;
    }
}