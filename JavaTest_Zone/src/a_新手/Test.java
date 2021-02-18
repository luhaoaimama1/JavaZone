package a_新手;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2017/3/18.
 */
public class Test {
    int k = 1;

    public Test() {
        System.out.println(k);
    }

    //
    public static boolean isSame(Class defineCls, Class objCls) {
        Class defineClsWarp = getWarpCls(defineCls);
        Class objClsWarp = getWarpCls(objCls);
        return defineClsWarp.isAssignableFrom(objClsWarp);//判断是不是多态类
    }

    private static Class getWarpCls(Class defineCls) {
        Class defineClsWarp = defineCls;
        if (defineCls.isPrimitive()) { //是基本类型
            if (defineCls == byte.class) {
                defineClsWarp = Byte.class;
            } else if (defineCls == short.class) {
                defineClsWarp = Short.class;
            } else if (defineCls == int.class) {
                defineClsWarp = Integer.class;
            } else if (defineCls == long.class) {
                defineClsWarp = Long.class;
            } else if (defineCls == float.class) {
                defineClsWarp = Float.class;
            } else if (defineCls == double.class) {
                defineClsWarp = Double.class;
            } else if (defineCls == char.class) {
                defineClsWarp = Character.class;
            } else if (defineCls == boolean.class) {
                defineClsWarp = Boolean.class;
            } else {
                //ignore
            }
        }
        return defineClsWarp;
    }

    public static void main(String[] args) {
        System.out.println("基本类型？：" + boolean.class.isPrimitive());
        System.out.println("基本类型？："+isSame(boolean.class,Boolean.class));


        System.out.println("实例：" + Serializable.class.isAssignableFrom(String.class));
        System.out.println("实例：" + Object.class.isAssignableFrom(String.class));
        System.out.println("实例：" + String.class.isAssignableFrom(String.class));

        System.out.println("aa:" + 0x10000000);
        System.out.println((2 | 4 | 8) * 10);
//        new Test();
        List<String> list = new ArrayList<>();

//        list.add(null);
//        list.add("1");
//        System.out.println( list.size());
        int offset = 3 * 60 * 1000 + 40 * 1000;
        int mins = offset / (60 * 1000);
        int mills = (offset % (60 * 1000)) / 1000;
        System.out.println(mins + ":" + mills);


        //数字格式化
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("00");
        System.out.println(df.format(77) + "-" + df.format(7));
        List<String> list2 = null;
        try {
            list2.get(0);
        } catch (Exception e) {
            return;
        }
        System.out.println("走吗");


    }
}
