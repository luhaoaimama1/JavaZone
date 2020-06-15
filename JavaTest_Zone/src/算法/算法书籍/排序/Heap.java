package 算法.算法书籍.排序;

import 算法.算法书籍.CompareUtils;

/**
 * 二叉树 ：那么就是N个数能除多少次2
 * <p>
 * 就是Log2,N 对数N不应该含有为0 , 那么a[0]站位。
 * <p>
 * 那么
 * 层数=floor(Log2,N)
 * 某个节点k
 * 上一个节点的坐标就是k/2
 * 子节点 2k,2k+1
 * 因为是最大优先，所以上面的是最大的
 *
 * @param <T>
 */
public class Heap {
    public static void main(String[] args) {
        Comparable[] arrays = 选择.arrays2;
        Heap.sort(arrays);
        CompareUtils.show(arrays);
    }

    /**
     * 把最大的元素取出来，然后和最后的交换，然后下沉a[1],边界是交换的位置-1；循环,直到边界为1
     */
    public static void sort(Comparable[] b) {
        Heap maxPQ = new Heap();
        //堆有序：少了最后一层的循环。 因为最后一层没必要下沉了 。
        int rightLastIndex = b.length;
        for (int i = rightLastIndex / 2; i >= 1; i--) {
            maxPQ.sink(b, i, rightLastIndex);
        }

        //把最大的元素取出来，然后和最后的交换，然后下沉a[1],边界是交换的位置-1；循环,直到边界为1
        while (rightLastIndex > 1) {//=1就排序完事了
            maxPQ.exch(b, 1, rightLastIndex--);
            maxPQ.sink(b, 1, rightLastIndex);
        }
    }

    /**
     * 下沉
     * 判断当前节点k 是否小于 两个子节点中大的那个。如果小于那么 互换，直到子节点 位置越界
     * 用于删除最上层的元素，交换成最后的元素 重新恢复成有序堆
     */
    public void sink(Comparable[] a, int k, int rightLastIndex) {
        while (2 * k <= rightLastIndex) {// 2k>rightLastIndex就越界了
            int j = 2 * k;
            //找到大的那个元素 。如果只有一个的话  就不比较了 注意边界 j+1<=rightLastIndex=》j < rightLastIndex
            if (j < rightLastIndex && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break; //当父节点不小于子节点 就不下沉了
            exch(a, k, j); //当父节点小于子节点 就交换
            k = j;
        }
    }

    public boolean less(Comparable[] a, int left, int right) {
        return a[left-1].compareTo(a[right-1]) < 0;
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = temp;
    }

}
