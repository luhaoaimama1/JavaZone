package a面试.排序;

/**
 * 请看笔记的图文分析。
 */
public class 快速排序 {

    public static void main(String[] args) {
        int[] array = {-7, -1, 9, 6, 5, 4, 1, 0, 8, 7, 3, 2, -2, -3};

        System.out.print("Before sort:");
        SortUtils.printArray(array);

        quickSortNB(array, 0, array.length - 1);

        System.out.print("After sort:");
        SortUtils.printArray(array);
    }


    public static void quickSortNB(int[] a, int start, int end) {
        if (start == end)
            return;
        int parIndex = par(a, start, end);
        if (parIndex > start)
            quickSortNB(a, start, parIndex - 1);
        if (parIndex < end)
            quickSortNB(a, parIndex + 1, end);


    }

    private static int par(int[] a, int start, int end) {
        //随机抽选一个 放到最后，这个就是 临界点
        int randomNumber = (int) (Math.random() * (end - start) + start);
        SortUtils.swap(a, randomNumber, end);

        int bigFist = start - 1;
        //与临界点对比，小于的话 计数 计数已经归纳的 第一个大的位置 ；
        for (int i = start; i < end; i++) {
            if (a[i] < a[end]) {
                //++是为了  1342 。2->3 的位置更换
                bigFist++;
                if (bigFist != i)//如果bigFist和当前的index不一样。就说明已经有大的出现了
                    SortUtils.swap(a, bigFist, i);

            }
        }

        //最后归纳第一个大的位置与 最后的位置更换
        bigFist++;//每次交换之前都得++ 因为bigFist右边那个才是归纳的大的位置
        SortUtils.swap(a, bigFist, end);
        return bigFist;
    }


}