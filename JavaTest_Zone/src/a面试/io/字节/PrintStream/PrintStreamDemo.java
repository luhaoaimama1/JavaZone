package a面试.io.字节.PrintStream; /**
 * 使用PrintStream进行输出
 * 并进行格式化
 * 也可以认为是一个辅助工具。主要可以向其他输出流，或者FileInputStream 写入数据，本身内部实现还是带缓冲的。
 * 本质上是对其它流的综合运用的一个工具而已。一样可以踢出IO 包！System.err和System.out 就是PrintStream 的实例！
 * */
import java.io.*;
public class PrintStreamDemo {
   public static void main(String[] args) throws IOException {
       String file3="/Users/fuzhipeng/Desktop"+File.separator+"test.txt";
       PrintStream print = new PrintStream(new FileOutputStream(file3));
       String name="Rollen";
       int age=20;
       print.printf("姓名：%s. 年龄：%d.",name,age);
       print.println(true);
       print.println("Rollen");
       print.close();
    }
}