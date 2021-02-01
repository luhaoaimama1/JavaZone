package 正则pattern;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fuzhipeng on 2018/6/20.
 */
public class 回车符检测 {
    public static void main(String[] args) {

        String str = "\n" +
                "我还是从前那个少年\n" +
                "\n" +
                "没有一丝丝改变（我就是很长啊啊啊的骄傲哇啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦！）\n"
                + "我还是从前那个少年3\n"
                + "没有回车啊";


        //匹配[] 排除[[] 这种情况  就是[  这里面不能有[的字符  ]
        Pattern p = Pattern.compile(".*\n");
        Matcher matcher = p.matcher(str);
        int start = 0;
        while (matcher.find()) {
            int end = matcher.end();
            System.out.println("matcher start:" + matcher.start() + "\t start:" + start + "\t end:" + end);
            //substring 包括start 不包括end
            String substring = str.substring(matcher.start(), end);
            System.out.println(substring.replaceAll("\n", "!空格!"));
            start = end;
        }
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
}
