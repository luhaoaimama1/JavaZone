package 正则pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fuzhipeng on 2016/10/19.
 */
public class Ma {
    public static void main(String[] args) {

        String str = "买菜5";
        str = str.replace('两', '二');
        Pattern p = Pattern.compile("([买菜])([零一二三四五六七八九十0-9].*[零一二三四五六七八九十百千万亿0-9元块毛角分]*)");
        Matcher matcher = p.matcher(str);
        if (matcher.find()) {
            //首先要想获得 group 必须 find
            //find 通过compile里面的 字符串  发现有匹配的字符串 就可以group到
            System.out.println("group()代表:整个匹配的!"+matcher.group());
            //group(1) group(2) 是在 group() 匹配到了 才能得到的
            System.out.println("group(1)代表:整个匹配的 第一个()的内容:"+matcher.group(1));
            System.out.println("group(2)代表:整个匹配的 第一个()的内容:"+matcher.group(2));
        }
    }
}
