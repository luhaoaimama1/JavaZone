package a面试.io.字节.管道测试;

import java.io.PipedOutputStream;

/**
 * 消息发送类
 * */
class Send implements Runnable{
   private PipedOutputStream out=null;
   public Send() {
       out=new PipedOutputStream();
    }
   public PipedOutputStream getOut(){
       return this.out;
    }
   public void run(){
       String message="hello , Rollen";
       try{
           out.write(message.getBytes());
       }catch (Exception e) {
           e.printStackTrace();
       }try{
           out.close();
       }catch (Exception e) {
           e.printStackTrace();
       }
    }
}