package 算法.算法书籍.排序;

import 算法.算法书籍.CompareUtils;

import static a面试.排序.SortUtils.sort;

public class 归并 {

    public static void main(String[] args) {
        Comparable[] arrays = 选择.arrays;
        Comparable[] saveArrays = new Comparable[arrays.length];
//        sort(arrays, 0, arrays.length - 1, saveArrays);
        sortFromFloor(arrays, saveArrays);
        CompareUtils.show(arrays);
    }

    //上来就分成最小的分 1，1+1=2，2+2=4  从底层向上合并
    public static void sortFromFloor(Comparable[] arrays, Comparable[] saveArrays) {
        for (int i = 1; i < arrays.length; i = i + i) { //代表分治的层数
            //每层  两两 合并，那么每一层递增就是两个两倍的数组长度。 倒数第一数组的时候 计算已经包括了最后的数组因为是两个两个计算的
            for (int j = 0; j < arrays.length - i; j = j + i + i) {
                merge(arrays, j, j + i - 1, Math.min(j + i + i - 1, arrays.length - 1), saveArrays);
            }
        }
    }

    //start 0 right size-1
    //自上到下的分 从下到上的合并
    public static void sort(Comparable[] arrays, int start, int right, Comparable[] saveArrays) {
        //结束条件 一个元素的时候
        if (start == right) return;
        int mid = (right + start) / 2;
        sort(arrays, start, mid, saveArrays);
        sort(arrays, mid + 1, right, saveArrays);
        merge(arrays, start, mid, right, saveArrays);
    }

    private static void merge(Comparable[] arrays, int start, int mid, int right, Comparable[] saveArrays) {
        //优化方案  如果mid<mid+1那么代表两边本来就是有有序的 不需要从新归并排序了
        if (!CompareUtils.less(arrays[mid + 1], arrays[mid])) return;

        //合并排序的元素
        for (int i = start; i <= right; i++) {
            saveArrays[i] = arrays[i];//复制一次值
        }

        int leftIndex = start;
        int rightIndex = mid + 1;

        //注意用的是辅助数组内的值进行对比。leftIndex和rightIndex都是辅助数组的index.
        for (int i = start; i <= right; i++) {
            //左半边用尽 取右半边的的元素
            if      (leftIndex > mid)                                                       arrays[i] = saveArrays[rightIndex++];
            //右半边用尽 取左半边的的元素
            else if (rightIndex > right)                                                    arrays[i] = saveArrays[leftIndex++];
            //如果右边的某个元素<左边的元素。那么当前元素=刚刚的右侧元素
            else if (CompareUtils.less(saveArrays[rightIndex], saveArrays[leftIndex]))      arrays[i] = saveArrays[rightIndex++];
            //其他情况就是使用左侧的元素
            else                                                                            arrays[i] = saveArrays[leftIndex++];
        }
    }
}
