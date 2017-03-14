package a面试.io.字符;

import java.util.Scanner;
/**
 *Scanner的小例子，从键盘读数据
 * */
public class ScannerDemo{
    public static void main(String[] args){
       Scanner sca = new Scanner(System.in);
//      读取一行  无限读取
//        while (true) {
//            System.out.println("请输入：");
//            listener.line(sc.nextLine());
//        }

       // 读一个整数
       int temp = sca.nextInt();
       System.out.println(temp);
       //读取浮点数
       float flo=sca.nextFloat();
       System.out.println(flo);
        //读取字符
       //...等等的，都是一些太基础的，就不师范了。
    }
}