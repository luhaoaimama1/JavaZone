package a面试.io.字节.管道测试;

import java.io.IOException;

/**
 * 测试类
 * */
class hello{
   public static void main(String[] args) throws IOException {
       Send send=new Send();
       Recive recive=new Recive();
        try{
//管道连接
           send.getOut().connect(recive.getInput());
       }catch (Exception e) {
           e.printStackTrace();
       }
       new Thread(send).start();
       new Thread(recive).start();
    }
}