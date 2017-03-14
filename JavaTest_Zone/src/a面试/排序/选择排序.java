package a面试.排序;

import java.util.Arrays;

/**
 * Created by fuzhipeng on 2017/3/12.
 *
 * 选择排序。   一个位置 L与所有的位置All_L，一次进行对比 和交换。一轮对比完，那个位置的值既最小
 * 时间复杂度  O(n²) 。 (n-1)次循环*(n)次循环
 */
public class 选择排序 {

    public static void main(String[] args) {

        //第一层循环确定  位置L
        for (int i = 0; i < SortUtils.sort.length - 1; i++) {
            //All_L 的的循环。需要在位置L的后边开始
            for (int j = i+1; j < SortUtils.sort.length ; j++) {
                SortUtils.rightBig(SortUtils.sort, i, j);
            }
        }
        System.out.println("value:" + Arrays.asList(SortUtils.sort));

    }
}
