package 工具;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fuzhipeng on 16/8/8.
 */
public class 空格测试2 {
    public static String content = "the sky is    blue";

    public static void main(String[] args) {
        Pattern pS = Pattern.compile("\\s+");
        Matcher mS = pS.matcher(content);
        while (mS.find()) {
            System.out.println("空格---->" +"\t 开始:" + mS.start() + "\t 结束:" + mS.end());
        }
    }

}
