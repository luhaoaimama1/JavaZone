package a面试.io.字符; /**
 * 字符流
 * 写入数据
 * */
import java.io.*;
public class FileWriterDemo{
   public static void main(String[] args) throws IOException {
       String fileName="/Users/fuzhipeng/Desktop"+File.separator+"test.txt";
       File f=new File(fileName);
       //new FileWriter(f,true); true追加内容  false覆盖
       Writer out =new FileWriter(f);
       String str="hello FileWriter!";
       out.write(str);
       out.close();
    }
}