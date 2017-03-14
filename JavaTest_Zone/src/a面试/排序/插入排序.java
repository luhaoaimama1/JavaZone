package a面试.排序;

import java.util.Arrays;

/**
 * Created by fuzhipeng on 2017/3/12.
 */
public class 插入排序 {
    public static void insertsort(Integer arr[]) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {//注意[0,i-1]都是有序的。如果待插入元素比arr[i-1]还大则无需再与[i-1]前面的元素进行比较了，反之则进入if语句
                int temp = arr[i];
                int j;
                for (j = i - 1; j >= 0 && arr[j] > temp; j--) {
                    arr[j + 1] = arr[j];//把比temp大或相等的元素全部往后移动一个位置
                }
                arr[j + 1] = temp;//把待排序的元素temp插入腾出位置的(j+1)
            }
        }

    }

    public static void main(String[] args) {
        Integer array[] = {4, 2, 1, 5};

        System.out.println("排序之前：" + Arrays.asList(array));
        insertsort(array);
        System.out.println("排序之后：" + Arrays.asList(array));
    }
}
