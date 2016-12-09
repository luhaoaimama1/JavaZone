package 语法糖;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fuzhipeng on 2016/12/2.
 */
public class Sugar {
    //看bin目录下的编译文件;
    public static void main(String[] args) {

        //泛型 自动装箱,自动拆箱,便利循环,变长参数;
        List<Integer> list= Arrays.asList(1,2,3,4);
        int sum=0;
        for (int integer : list) {
            sum+=integer;
        }
        System.out.println(sum);
    }

//    // FIXME: 2016/12/2  下面两个,类型擦除 后是一样的所以不行
//    public static void ak(List<String> stringList){
//
//    }
//    public static void ak(List<Integer> intList){
//
//    }
}
