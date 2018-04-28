package a面试.排序;

/**
 * Created by fuzhipeng on 2017/3/12.
 */
public class SortUtils {
    public static Integer[] sort = new Integer[]{0, 3, 2, 1, 9, 8, 5, 6, 7, 4};

    public static void rightBig(Integer[] sort, int left, int right) {
        if (sort[left] > sort[right])
            swap(sort,left,right);
    }

    public static void swap(Integer[] sort, int left, int right) {
        Integer temp = sort[left];
        sort[left] = sort[right];
        sort[right] = temp;
    }
    public static void swap(int[] sort, int left, int right) {
        Integer temp = sort[left];
        sort[left] = sort[right];
        sort[right] = temp;
    }


    public static void printArray(int[] array) {
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

}
