package 算法.算法书籍.排序;

import 算法.算法书籍.CompareUtils;

public class 选择 {
    public static Comparable[] arrays = new Comparable[]{
            "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"
    };
    public static Comparable[] arrays3 = new Comparable[]{
            "S", "O", "R", "T", "E", "X", "A", "M", "P", "L"
    };
//    static Comparable[] arrays2 = new Comparable[]{
//            6,9,8,7,6
//    };

    public static Comparable[] arrays2 = new Comparable[]{
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 1, 3, 4, 5, 6,
    };

    public static void main(String[] args) {
        //tips:最后一次 其实不用交换了因为就剩下自己了  不过暂时先不改 因为交换和比较的次数这么算比较容易
        for (int i = 0; i < arrays.length; i++) {
            int minIndex = i;
            for (int j = i; j < arrays.length; j++) {
                //倒着来的话 1，2，n-1, n的求和 就是约等于n*n/2
                if (CompareUtils.less(arrays[j], arrays[minIndex])) minIndex = j;
            }
            //交换 N
            CompareUtils.exch(arrays, i, minIndex);
        }

        CompareUtils.show(arrays);
    }
}
