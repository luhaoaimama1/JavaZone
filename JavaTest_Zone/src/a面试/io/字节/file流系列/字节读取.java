package a面试.io.字节.file流系列; /**
 * 字节流
 * 读文件内容,节省空间
 * */
import java.io.*;
class 字节读取 {
   public static void main(String[] args) throws IOException {
       File f=new File("/Users/fuzhipeng/IdeaProjects/JavaZone/JavaTest_Zone/src/a面试/io/test");
       InputStream in=new FileInputStream(f);
       //file.length获取字节数
       byte[] b=new byte[(int)f.length()];
       for (int i = 0; i < b.length; i++) {
           b[i]=(byte)in.read();
       }
       in.close();
       System.out.println(new String(b));
    }
}