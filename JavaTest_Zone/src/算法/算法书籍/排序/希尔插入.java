package 算法.算法书籍.排序;

import 算法.算法书籍.CompareUtils;

public class 希尔插入 {

    //主要解决的是插入排序的 大规模乱序 加入最后一位是最小的那么他移动到最开始的位置要一次一步的移动 而这个不是一次一步 是一次一大步。
    public static void main(String[] args) {
        Comparable[] arrays = 选择.arrays;

        int h = 1;
        while (h < arrays.length / 3) h = h * 3 + 1; //这个递增序列 有很多不一样的版本

        while (h >= 1) {
            for (int i = h; i < arrays.length; i++) {
                //插入的元素 与之前的元素做对比 如果之前的大 那么交换 然后--， 直到比他小位置
                for (int j = i; j >= h && CompareUtils.less(arrays[j], arrays[j - h]); j-=h) {
                    CompareUtils.exch(arrays, j, j - h);
                }
            }
            h = h / 3; //递减序列
        }

        CompareUtils.show(arrays);
    }
}
