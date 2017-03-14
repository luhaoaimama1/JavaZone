package a面试;


import java.io.BufferedInputStream;
import java.io.File;

/**
 * Created by fuzhipeng on 2017/3/10.
 */
public class FileFilter {

    public static void main(String[] args) {
        File file=new File(".");
//        File file2=new File("/Users/fuzhipeng/blog");
        String[] arrs = file.list();
        for (int i = 0; i <arrs.length; i++) {
            System.out.println( arrs[i].toString());
        }
//        ScannerUtils.addListener(new ScannerUtils.LineListener() {
//            @Override
//            public void line(String lineString) {
//                System.out.println("已经输入====》" + lineString);
//
//            }
//        });

    }
}
