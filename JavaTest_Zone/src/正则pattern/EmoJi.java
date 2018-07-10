package 正则pattern;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fuzhipeng on 2018/6/20.
 */
public class EmoJi {
    public static void main(String[] args) {
        String str = "？ad[ab]adaljfdaj[白眼]为啥哭能[黑天]独立？[] [dda[da]b?]";
        //匹配[] 排除[[] 这种情况  就是[  这里面不能有[的字符  ]
        Pattern p = Pattern.compile(escapeExprSpecialWord("[")
                + "([.&[^"+escapeExprSpecialWord("[")+"]]+?)"
                + escapeExprSpecialWord("]"));
        Matcher matcher = p.matcher(str);
        while (matcher.find()) {
            System.out.println(str.substring(matcher.start(), matcher.end()));
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
