package gson学习与反射.gson.数组工具测试;

import sun.invoke.empty.Empty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fuzhipeng on 2016/10/21.
 */
public class ArraysZ {

    public static <T> List<T> asList(T... a) {
        List<T> result=new ArrayList<>();
        for (T t1 : a)
            result.add(t1);
        return result;
    }

    public static <T> T[] asArray(List<T> a) {
        //a.getClass().getTypeParameters()  这样的话泛型会被擦除掉
        //解决办法: 获取第一个值 得到他的类~  a.get(0).getClass()
        T[] ts= (T[]) Array.newInstance(a.get(0).getClass(), a.size());
        for (int i = 0; i < a.size(); i++)
            ts[i]=a.get(i);
        return  ts;
    }

    public static void main(String[] args) {
        String[]  strings=new String[]{
                "1","2","3","4","5" };
        List<String> list = ArraysZ.asList(strings);
        String[] ms = ArraysZ.asArray(list);
        System.out.println(Arrays.toString(ms));
    }
}
