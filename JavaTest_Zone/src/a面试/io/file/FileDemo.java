package a面试.io.file;

import java.io.*;
public class FileDemo{
   public static void main(String[] args) {
       /**
        * 此处多说几句：有些同学可能认为，我直接在windows下使用\进行分割不行吗？
        * 当然是可以的。但是在linux下就不是\了。所以，要想使得我们的代码跨平台，
        * 更加健壮，所以，大家都采用这两个常量吧，其实也多写不了几行。
        */
       //  "/"
       System.out.println(File.separator);
       //  ":"
       System.out.println(File.pathSeparator);
    }
}