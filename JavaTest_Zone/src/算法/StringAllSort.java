package 算法;

import java.util.TreeSet;

/**
 * Created by fuzhipeng on 2018/4/27.
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。 结果请按字母顺序输出
 * <p>
 * 解题思路：  第一位是变的。与其他交换(包含自己)。 交换后 。递归->在这个上 对后一位与后面进行交换 (包含自己)而第一位则不管。
 * 当变换位置为 最后一位的时候 。添加这个字符串。 再由 treeset进行重复过滤
 * abc
 * <p>
 * a bc  a bc
 * a cb
 * <p>
 * b ac  b ac
 * b ca
 * <p>
 * c ba  c ba
 * c ab
 */
public class StringAllSort {

    public static void main(String[] args) {
        print("abc".toCharArray(), 0);
        for (String s : treeSet) {
            System.out.println(s);
        }
    }

    static TreeSet<String> treeSet = new TreeSet<String>();

    public static void print(char[] chars, int index) {
        if (index > chars.length - 1 || index < 0) return;
        if (index == chars.length - 1)//最后一位的时候添加
            treeSet.add(new String(chars));
        else {
            for (int i = index; i < chars.length; i++) {
                swap(chars, index, i);//交换位置
                print(chars, index + 1);
                swap(chars, i, index);//交换回来
            }
        }

    }

    private static void swap(char[] chars, int left, int right) {
        char temp = chars[right];
        chars[right] = chars[left];
        chars[left] = temp;
    }
}
