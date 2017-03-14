package a面试.io.字节.file流系列;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by fuzhipeng on 2017/3/10.
 */
public class FileInputStreamDemo {
    //机器中的编码都是 二进制编码 显示的时候 为了让人看懂 从而设置了各种字符集 ,如果与原来的编码不一致则会导致看不懂既乱码 。
    //机器的二进制码没有变化 ,仅仅是 对照的字符集 不一致 导致的

    public static void main(String[] args) throws IOException {
        System.out.println("默认字符集==="+Charset.defaultCharset());

        File file=new File("/Users/fuzhipeng/IdeaProjects/JavaZone/JavaTest_Zone/src/a面试/io/test");
        FileInputStream in = new FileInputStream(file);
        byte[] b=new byte[in.available()];
        in.read(b);
        in.close();
        System.out.println(new String(b));

    }
}
