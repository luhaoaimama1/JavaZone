package 工具;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Scanner;

/**
 * Created by fuzhipeng on 16/9/7.
 */
public class ColorParse {

    //    69 75 89, 0.90
//    69 75 89
    public static void main(String[] args) throws Exception {
        String abc = new String("69 75 89, 0.90");
        Scanner sc = new Scanner(System.in);

//        System.out.println("输入第一个数字:");
//        System.out.println("输入数字："+sc.nextInt());
//
//        System.out.println("输入第一个boolean值(true/false):");
//        if(sc.nextBoolean()){
//            System.out.println("输入布尔：真的");
//        }else{
//            System.out.println("输入布尔：假的");
//        }
//        System.out.println("输入一个长整型:");
//        System.out.println("输入长整型："+sc.nextLong());

        while (true) {
            System.out.println("输入一个字符串:");
            try {
                print(sc.nextLine());
            } catch (Exception e) {
                System.out.println("格式不正确");
            }

        }


    }

    private static void print(String abc) {

        String[] result = abc.trim().split(",");
//        System.out.println(result[0]);
        String r, g, b, a = "1";

        r = result[0].split(" ")[0];
        g = result[0].split(" ")[1];
        b = result[0].split(" ")[2];


        String rS = Integer.parseInt(r)==0?"00":Integer.toHexString(Integer.parseInt(r));
        String gS = Integer.parseInt(g)==0?"00":Integer.toHexString(Integer.parseInt(g));
        String bS = Integer.parseInt(b)==0?"00":Integer.toHexString(Integer.parseInt(b));
//        System.out.println((int) (Double.parseDouble(a)*255));

        System.out.print("#");
        if (result.length != 1) {
            a = result[1];
            String aS = Integer.toHexString((int) (Double.parseDouble(a) * 255));
            System.out.print(aS);
        }
        System.out.print(rS + gS + bS );
        System.out.println();

    }
}
