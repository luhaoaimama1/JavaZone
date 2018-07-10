package a_新手;

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

    public static void main(String[] args) {

        System.out.println("aa:"+0x10000000);
        System.out.println((2 | 4 | 8) * 10);
//        new Test();
        List<String> list=new ArrayList<>();

//        list.add(null);
//        list.add("1");
//        System.out.println( list.size());
        int offset=3 * 60 * 1000+40 * 1000;
        int mins=offset/(60 * 1000);
        int mills=(offset%(60 * 1000))/1000;
        System.out.println(mins+":"+mills);


        //数字格式化
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("00");
        System.out.println( df.format(77)+"-"+ df.format(7));
        List<String> list2=null;
        try {
            list2.get(0);
        } catch (Exception e) {
          return;
        }
        System.out.println("走吗");

    }
}
