package 算法.算法书籍.排序;

import edu.princeton.cs.algs4.Insertion;
import 算法.算法书籍.CompareUtils;

public class 快速排序 {
    public static void main(String[] args) {
        Comparable[] arrays = 选择.arrays2;
//        sort(arrays, 0, arrays.length - 1);
//        CompareUtils.show(arrays);
        fastThreeSplit(arrays, 0, arrays.length - 1);
        CompareUtils.show(arrays);
    }

    public static int par(Comparable[] arrays, int left, int right) {
        Comparable v = arrays[left];
        int scanLeft = left, scanRight = right + 1;
        while (true) {
            //找到比v大的  小于v的话你就一直找吧 直到尽头 或者大于等于
            while (CompareUtils.less(arrays[++scanLeft], v)) {
                if (scanLeft == right) break;
            }
            //为什么要用--scanRight而不能用scanRight--呢 因为--scanRight 代表scanRight=scanRight-1 代表操作后才检测。
            //而scanRight--的话 代表 先用scanRight检测 然后-- 那么检测的结果最后可能越界 因为--那个没有检测
            //所以 要对检测的 操作要  符号在前  更改完值的检测 。
            //找比v小的。 大于v的话你就一直找吧 直到  小于等于 或者尽头  到头的话 就是相等了，就是false，
            while (CompareUtils.less(v, arrays[--scanRight])) {
//                if (scanRight == left) break;//这里不用谢因为left就是v less <的话就一定是false
            }//如果小于就继续 直到 找到比v大的

            if (scanRight <= scanLeft) break;
            CompareUtils.exch(arrays, scanLeft, scanRight);
        }
        //对切分的元素与 找到的最后的小于的元素交换 因为第一个位置是小于的元素
        CompareUtils.exch(arrays, left, scanRight);
        return scanRight;
    }

    public static void sort(Comparable[] arrays, int left, int end) {
        if (left >= end) return;
//优化
        //10=5-15之间都很好  小数组的话 用插入 会提升效率
//        if (end <= left + 10) {
//            Insertion.sort(arrays, left, end) return;
//        }
        int splitIndex = par(arrays, left, end);
        sort(arrays, left, splitIndex - 1);
        sort(arrays, splitIndex + 1, end);
    }


    /**
     * @param arrays
     * @param lo
     * @param hi
     */
    public static void fastThreeSplit(Comparable[] arrays, int lo, int hi) {

        if (lo >= hi) return;
        Comparable splitObj = arrays[lo];

        int i = lo + 1, p = lo + 1, q = hi, j = hi;

        /**
         * arrays
         * [lo,p] =v  [p,j]<v  [i,q]>v [q,hi-1]=v
         * [lo,j]<v  [j,i]=v  [i,hi]>v  //把等号的放中间
         */
        while (true) {
            while (true) {
                if (i == hi) break; //为什么一定要写在前面呢？
                // 很重要 因为值要有更改操作 对比操作是更改之前对比的。所以 判断要写在前面
                // 如果先做 值修改  就可以吧判断写在后边。在做对比操作的 话
                //最终结论 判断呢 一定要在   对比就是取值的之前判断。

                int i1 = arrays[i].compareTo(splitObj);
                if (i1 < 0) {
                    i++;
                } else if (i1 == 0) {
                    if (i != p) CompareUtils.exch(arrays, p, i);
                    i++;
                    p++;
                } else break;//找到大于的了
            }

            while (true) {
                if (j == lo) break; //为什么一定要写在前面呢？ 很重要
                int i1 = arrays[j].compareTo(splitObj);
                if (i1 > 0) {
                    j--;
                } else if (i1 == 0) {
                    if (j != q) CompareUtils.exch(arrays, q, j);
                    j--;
                    q--;
                } else break;//找到小于的了
            }
            if (i >= j) break;
            CompareUtils.exch(arrays, i, j);
        }
        // j是小于的元素结束  i是大于的元素的开头
        CompareUtils.exch(arrays, lo, j);//与开头交换
        // j是=元素 是小于的右边  i是大于的元素的开头


        //step2.等号部分放入中间
        //pq 不用包括 因为之前是 先复制 在做+-的
        for (int i1 = lo + 1; i1 < p; i1++) {
            CompareUtils.exch(arrays, i1, --j);
        }

        for (int i1 = hi; i1 > q; i1--) {
            CompareUtils.exch(arrays, i1, i++);
        }

        fastThreeSplit(arrays, lo, j - 1);
        fastThreeSplit(arrays, i, hi);
    }
}
