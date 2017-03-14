package a面试.io.字符; /**
 * 字符流
 * 从文件中读出内容
 * */
import java.io.*;
public class FileReaderDemo{
   public static void main(String[] args) throws IOException {
       String fileName="/Users/fuzhipeng/Desktop"+File.separator+"test.txt";
       File f=new File(fileName);
       char[] ch=new char[100];
       Reader read=new FileReader(f);
       int temp=0;
       int count=0;
       while((temp=read.read())!=(-1)){
           ch[count++]=(char)temp;
       }
       read.close();
       System.out.println("内容为"+new String(ch,0,count));
    }
}