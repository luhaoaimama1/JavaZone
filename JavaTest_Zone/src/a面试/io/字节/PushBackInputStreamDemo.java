package a面试.io.字节;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
  
/**
 * 【案例】PushBackInputStream回退流操作
 * */
public class PushBackInputStreamDemo{
    public static void main(String[] args) throws IOException {
       String str = "文件写入字符串,rollenholt";
       PushbackInputStream push = null;
       ByteArrayInputStream bat = null;
       bat = new ByteArrayInputStream(str.getBytes());
       push = new PushbackInputStream(bat);
       int temp = 0;
       while((temp = push.read()) != -1){
           if(temp == ','){
                push.unread(temp);
                temp = push.read();
                System.out.print("(回退" +(char) temp + ") ");
           }else{
                System.out.print((char) temp);
           }
       }
    }
}