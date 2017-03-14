package a面试.io.file;

import java.io.*;
public class 创建空文件{
   public static void main(String[] args) {

       String fileName="/Users/fuzhipeng/Desktop"+File.separator+"test2.txt";
       File f=new File(fileName);
       try{
           //创建文件
           f.createNewFile();
       }catch (Exception e) {
           e.printStackTrace();
       }


       if(f.exists()){
           //删除文件
           System.out.println("文件存在 已经删除"+ f.delete());
       }else{
           System.out.println("文件不存在");
       }
    }
}