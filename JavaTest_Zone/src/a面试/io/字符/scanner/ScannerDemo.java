package a面试.io.字符.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
  
/**
 *Scanner的小例子，从文件中读内容
 * */
public class ScannerDemo{
   public static void main(String[] args){
       String fileName="/Users/fuzhipeng/Desktop"+File.separator+"test.txt";
       File file = new File(fileName);
       Scanner sca = null;
       try{
           sca = new Scanner(file);
       }catch(FileNotFoundException e){
           e.printStackTrace();
       }
       String str = sca.next();
       System.out.println("从文件中读取的内容是：" + str);
    }
}