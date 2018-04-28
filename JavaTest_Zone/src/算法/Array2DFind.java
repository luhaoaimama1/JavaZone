package 算法;

/**
 * Created by fuzhipeng on 2018/4/26.
 * 二维数组中查找
 * <p>
 * 题目：
 * <p>
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 *
 * 思路：查找的值。小于9 最后一列，清除，小于倒数第二列清除。大于2 第一行清除。大于4第二行清除
 * 规律：首先选取数组最右的数字，查找小于  那么此列中没有。大于的话  此行中没有。等于你懂的
 */
public class Array2DFind {
    private static int[][] array = new int[][]{
            {1, 2, 8, 9},
            {2, 4, 9, 12},
            {4, 7, 10, 13},
            {6, 8, 11, 17}
    };

    public static void main(String[] args) {
        System.out.println("找到7了吗："+find(array,7));
        System.out.println("找到8了吗："+find(array,8));
        System.out.println("找到18了吗："+find(array,18));
        System.out.println("找到5了吗："+find(array,5));
    }

    public static boolean find(int[][] array, int number) {
        boolean find = false;
        int row = 0;
        int column = array[0].length - 1;
        while (row < array.length && column >= 0) {
            if (array[row][column] > number) {
                column--;
            } else if (array[row][column] < number) {
                row++;
            } else {
                find = true;
                break;
            }

        }
        return find;
    }
}
