package 算法.导论;

import 算法.算法书籍.排序.选择;

public class 中位数 {

    public static void main(String[] args) {
        Comparable[] arrays = 选择.arrays3;
        sort(arrays);
    }

    public static void sort(Comparable[] s) {
        Comparable min = s[0];
        Comparable max = s[0];
        for (int i = 1; i < s.length; i++) {
            Comparable comparable = s[i];
            if (compare(min, comparable) <= 0) {
                min = comparable;
            } else if (compare(max, comparable) > 0) {
                max = comparable;
            }
        }
        System.out.println( min+" , \t "+ max +" ,\t count="+count  +"\t length:"+s.length);
    }
    public static int count=0;

    private static int compare(Comparable max, Comparable comparable) {
        count++;
        return comparable.compareTo(max);
    }
}
