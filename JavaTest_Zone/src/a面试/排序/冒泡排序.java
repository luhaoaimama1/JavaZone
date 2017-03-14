package a面试.排序;

import java.util.Arrays;

/**
 * Created by fuzhipeng on 2017/3/12.
 * 冒泡是 左右相邻比较
 *
 * 时间复杂度  O(n²) 。
 */
public class 冒泡排序 {

    public static void main(String[] args) {
        //second:i中的sort.length-1 是因为如果10个数 9个大数已经冒泡上去了那么最后一个就是最小的 不需要再排序了
        for (int i = 0; i < SortUtils.sort.length - 1; i++) {
            //first：从内层循环分析
            //10个数  相邻的两个数 进行比较 循环一次 则把最大的数冒泡到右边；
            //sort.length - 1是防止 数组越界 最后一个不需要后一个数作对比
            for (int j = 0; j < (SortUtils.sort.length - 1 - i); j++) {
                SortUtils.rightBig(SortUtils.sort, j, j + 1);
            }
        }
        System.out.println("value:" + Arrays.asList(SortUtils.sort));

    }


}
