package a面试.io.字节.file流系列; /**
 * 字节流
 * 向文件中写入字符串
 * */
import java.io.*;
class 文件写入字符串 {
   public static void main(String[] args) throws IOException {
       String fileName="/Users/fuzhipeng/Desktop"+File.separator+"test.txt";
       File f=new File(fileName);
       //new FileOutputStream(f,true);//true表示追加模式，否则为覆盖
       OutputStream out =new FileOutputStream(f);
       String str="Hello World";
       byte[] b=str.getBytes();
       out.write(b);
       out.close();
    }
}