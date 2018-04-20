package a面试.排序;

/**
 * 请看笔记的图文分析。
 */
public class 快速排序 {

    public static void main(String[] args) {
        int[] array = {-7, -1, 9, 6, 5, 4, 1, 0, 8, 7, 3, 2, -2, -3};

        System.out.print("Before sort:");
        SortUtils.printArray(array);

        quickSort(array, 0, array.length - 1);

        System.out.print("After sort:");
        SortUtils.printArray(array);
    }


    /**
     *   已第一个为基准点分割，比他小的放左边，大的放右边。 递归使用直到 start end相邻的时候；
     */
    public static void quickSort(int a[], int start, int end) {
        int i, j;
        i = start;
        j = end;
        if ((a == null) || (a.length <= 1))
            return;

        while (i < j) {//查找基准点下标
            while (i < j && a[j] > a[i])
                j--;
            if (i < j) { // 右侧扫描，找出第一个比key小的，交换位置
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
            while (i < j && a[i] < a[j])
                // 左侧扫描（此时a[j]中存储着key值）
                i++;
            if (i < j) { // 找出第一个比key大的，交换位置
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        if (i - start > 1) { // 递归调用，把key前面的完成排序
            quickSort(a, 0, i - 1);
        }
        if (end - j > 1) {
            quickSort(a, j + 1, end); // 递归调用，把key后面的完成排序
        }
    }


}