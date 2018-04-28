package 算法;

import java.util.*;

/**
 * Created by fuzhipeng on 2018/4/27.
 * <p>
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0
 */
public class ArrayHalfNumber {

    public static void main(String[] args) {
        Integer[] numbers = new Integer[]{1, 2, 3, 2, 2, 2, 5, 4, 2};

        //排序后， 如果一个数连续与临时的数相同 则加次数  达到一半 break;
        List<Integer> list = Arrays.asList(numbers);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : -1;
            }
        });

        int time = 0;
        int value = 0;
        for (Integer integer : list) {
            if (time == 0) {
                value = integer;
                time++;
            } else {
                if (value == integer) {
                    time++;
                    if (time >= list.size() / 2)
                        break;
                }else{
                    value = integer;
                    time=1;
                }
            }
        }
        System.out.println(value);

    }
}
