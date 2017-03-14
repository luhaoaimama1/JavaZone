package a面试.io.字节;

import java.io.*;

/**
 * 将两个文本文件合并为另外一个文本文件
 */
public class SequenceInputStreamDemo {
    public static void main(String[] args) throws IOException {
        String str="流1";
        String str2="流2";
        String file3="/Users/fuzhipeng/Desktop"+File.separator+"test.txt";
        InputStream input1 = new ByteArrayInputStream(str.getBytes());
        InputStream input2 = new ByteArrayInputStream(str2.getBytes());
        OutputStream output = new FileOutputStream(file3);
        // 合并流
        SequenceInputStream sis = new SequenceInputStream(input1, input2);
        System.out.println(sis.available());
        int temp = 0;
        while ((temp = sis.read()) != -1) {
            output.write(temp);
        }
        input1.close();
        input2.close();
        output.close();
        sis.close();
    }
}