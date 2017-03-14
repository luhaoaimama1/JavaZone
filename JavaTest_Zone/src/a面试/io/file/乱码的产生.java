package a面试.io.file;

import java.io.*;

/**
 * 乱码的产生
 * */
public class 乱码的产生 {
    //输出结果为乱码，系统默认编码为：UTF-8，而此处编码为ISO8859-1
    public static void main(String[] args) throws IOException {
        //       取得本地的默认编码
        System.out.println("系统默认编码为："+ System.getProperty("file.encoding"));

        String fileName="/Users/fuzhipeng/Desktop"+File.separator+"test2.txt";
        OutputStream out = new FileOutputStream(fileName);
        byte[] bytes = "你好".getBytes("ISO8859-1");
        out.write(bytes);
        out.close();
    }
}