package a面试.io.字节.PrintStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
  
/**
 * 为System.out.println()重定向输出
 * */
public class 输出重定向 {
   public static void main(String[] args){
       // 此刻直接输出到屏幕
       System.out.println("hello");
       PrintStream control = System.out;
       String fileStr="/Users/fuzhipeng/Desktop"+File.separator+"test.txt";
       File file = new File(fileStr);
       try{
           System.setOut(new PrintStream(new FileOutputStream(file)));
       }catch(FileNotFoundException e){
           e.printStackTrace();
       }
       System.out.println("这些内容在文件中才能看到哦！");
       //重新定向回来   Tips 直接 System.setOut(System.out); 是错误的 需要把原来的留下来
       System.setOut(control);
       System.out.println("重新定向回来！");
    }
}