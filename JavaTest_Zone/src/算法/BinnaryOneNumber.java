package 算法;

/**
 * Created by fuzhipeng on 2018/4/27.
 * <p>
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class BinnaryOneNumber {
    public static void main(String[] args) {
        System.out.println("11二进制的个数："+numberOf1(11));

    }

    public  static int numberOf1(int n) {
        int number = 0;
        String numStr = Integer.toBinaryString(n);
        char[] chars = numStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1')
                number++;
        }
        return number;
    }
}
