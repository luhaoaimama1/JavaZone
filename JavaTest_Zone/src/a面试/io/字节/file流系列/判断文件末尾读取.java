package a面试.io.字节.file流系列; /**
 * 字节流
 *读文件
 * */
import java.io.*;
class 判断文件末尾读取 {
   public static void main(String[] args) throws IOException {
       File f=new File("/Users/fuzhipeng/IdeaProjects/JavaZone/JavaTest_Zone/src/a面试/io/test");
       InputStream in=new FileInputStream(f);
       byte[] b=new byte[1024];
       int count =0;
       int temp=0;
       //是否为-1
       while((temp=in.read())!=(-1)){
           b[count++]=(byte)temp;
       }
       in.close();
       System.out.println(new String(b));
    }
}