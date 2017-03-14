package a面试.io.字节.PrintStream;

import java.io.*;

/**
 * System.in重定向
 */
public class 输入重定向 {
    public static void main(String[] args) {
        System.setIn(new ByteArrayInputStream("定性OK不?".getBytes()));
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            len = System.in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("读入的内容为：" + new String(bytes, 0, len));
    }
}