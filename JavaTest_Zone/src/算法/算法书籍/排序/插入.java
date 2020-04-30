package 算法.算法书籍.排序;

import 算法.算法书籍.CompareUtils;

public class 插入 {
    public static void main(String[] args) {
        Comparable[] arrays = 选择.arrays;

        for (int i = 1; i < arrays.length; i++) {
            //插入的元素 与之前的元素做对比 如果之前的大 那么交换 然后--， 直到比他小位置
            for (int j = i; j > 0 && CompareUtils.less(arrays[j], arrays[j - 1]); j--) {
                CompareUtils.exch(arrays, j, j - 1);
            }
        }
        CompareUtils.show(arrays);

        //交换不固定   最好的情况  不交换 ，最坏的情况： 每次循环都权交换 每次的都是最小的 1..n-1 =n(和)*n/2(次数)
        //比较也不固定 最好的情况  排序好 每次只比较一次，那么就是n-1次；        最坏的情况： 每次的都是最小的 1..n-1 =n(和)*n/2(次数)

    }
}
