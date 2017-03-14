package a面试;

import java.util.Scanner;

/**
 * Created by fuzhipeng on 2017/3/10.
 */
public class ScannerUtils {

    public static void addListener(LineListener listener) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入：");
            listener.line(sc.nextLine());
        }
    }

    public interface LineListener {
        public void line(String lineString);
    }
}
