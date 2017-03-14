package a面试.io.字节.PrintStream; /**
 * 使用OutputStream向屏幕上输出内容
 * */
import java.io.*;
public class 控制器打印 {
   public static void main(String[] args) throws IOException {
       OutputStream out=System.out;
       try{
           out.write("helloWoCa?".getBytes());
       }catch (Exception e) {
           e.printStackTrace();
       }
       try{
           out.close();
       }catch (Exception e) {
           e.printStackTrace();
       }
    }
}