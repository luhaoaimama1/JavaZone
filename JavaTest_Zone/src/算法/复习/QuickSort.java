package 算法.复习;

import 算法.算法书籍.CompareUtils;
import 算法.算法书籍.排序.选择;

public class QuickSort {


    public static void main(String[] args) {
        Comparable[] arrays = 选择.arrays2;
        quickSort(arrays);
        CompareUtils.show(arrays);
    }

    public static void quickSort(Comparable[] arrays) {
        quickSort(arrays, 0, arrays.length - 1);
    }

    public static void quickSort(Comparable[] arrays, int left, int right) {
        if (left >= right) return; 

        int k = kSort(arrays, left, right);
        //k不用排序了 因为他得顺序已经排好了
        quickSort(arrays, left, k - 1);
        quickSort(arrays, k + 1, right);
    }

    private static int kSort(Comparable[] arrays, int left, int right) {
        Comparable kValue = arrays[left];
        int kIndex = left;

        for (int i = left + 1; i <= right; i++) {
            if (kValue.compareTo(arrays[i]) > 0) {
                for (int j = i; j > kIndex; j--) {
                    exch(arrays, j, j - 1);
                }
                kIndex++;
            }
        }
        return kIndex;
    }

    public static void exch(Comparable[] arrays, int a, int b) {
        Comparable tempA = arrays[a];
        arrays[a] = arrays[b];
        arrays[b] = tempA;
    }
}
