package a面试.io.字节.管道测试;

import java.io.PipedInputStream;

/**
 * 接受消息类
 * */
class Recive implements Runnable{
   private PipedInputStream input=null;
   public Recive(){
       this.input=new PipedInputStream();
    }
   public PipedInputStream getInput(){
       return this.input;
    }
   public void run(){
       byte[] b=new byte[1000];
       int len=0;
       try{
           len=this.input.read(b);
       }catch (Exception e) {
           e.printStackTrace();
       }try{
           input.close();
       }catch (Exception e) {
           e.printStackTrace();
       }
       System.out.println("接受的内容为 "+(new String(b,0,len)));
    }
}