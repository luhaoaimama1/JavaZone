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
public class MaxPQ<T extends Comparable<T>> {

    public static void main(String[] args) {
        Integer[] arrays = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 1, 3, 4, 5, 6,
        };
        MaxPQ<Integer> maxPQ = new MaxPQ(arrays.length);
        for (Integer array : arrays) {
            maxPQ.insert(array);
        }
        System.out.println("个数：" + maxPQ.size());
        while (maxPQ.size() != 0) {
            System.out.print(maxPQ.popMax() + "\t");
        }
    }

    T[] a;
    int objSize;

    public MaxPQ(int size) {
        a = (T[]) new Comparable[size + 1];
        objSize = 0;
    }

    /*
     * 上浮  用于添加元素后，恢复成有序堆
     *
     * 上浮：把新元素放到最后，然后和父节点对比 如果比父节点大就交换，继续上浮直到 k/2==0 break;
     */
    public void swim(int k) {
        //然后上浮k
        while (k / 2 > 0 && less(k / 2, k)) {
            //k/2==0 就退出了
            //父元素小于当前元素 交换, 大于等于就退出
            exch(a, k / 2, k);
            k = k / 2;
        }
    }

    //添加元素插入到结尾。然后上浮这个 成有序堆
    public void insert(T obj) {
        a[++objSize] = obj;
        swim(objSize);
    }

    /**
     * 下沉
     * 判断当前节点k 是否小于 两个子节点中大的那个。如果小于那么 互换，直到子节点 位置越界
     * 用于删除最上层的元素，交换成最后的元素 重新恢复成有序堆
     */
    public void sink(int k, int rightLastIndex) {
        while (2 * k <= rightLastIndex) {// 2k>rightLastIndex就越界了
            int j = 2 * k;
            //找到大的那个元素 。如果只有一个的话  就不比较了
            //注意边界 j+1<=rightLastIndex=》j < rightLastIndex
            if (j < rightLastIndex && less(j, j + 1)) j++;
            //当父节点不小于子节点 就不下沉了
            if (!less(k, j)) break;
            //当父节点小于子节点 就交换
            exch(a, k, j);
            k = j;
        }
    }

    public boolean less(int left, int right) {
        return a[left].compareTo(a[right]) < 0;
    }

    public void exch(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public int size() {
        return objSize;
    }

    /**
     * 获取最大元素既a[1],然后把最后的元素放到a[1]上。然后下沉 恢复成有序堆
     *
     * @return
     */
    public T popMax() {
        if (objSize >= 1) {
            T result = a[1];
            a[1] = a[objSize];//最后的
            a[objSize--] = null;//防止游离
            sink(1, objSize);
            return result;
        } else throw new IllegalStateException("越界了！");
    }
}
