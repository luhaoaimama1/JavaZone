package a面试.io.字节;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class DataOutputStreamDemo{
   public static void main(String[] args) throws IOException{
       String file="/Users/fuzhipeng/Desktop"+File.separator+"test.txt";
       char[] ch = { 'A', 'B', 'C' };
       DataOutputStream out = null;
       out = new DataOutputStream(new FileOutputStream(file));
       for(char temp : ch){
           out.writeChar(temp);
       }
       out.close();
    }
}