package 算法;

/**
 * Created by fuzhipeng on 2018/4/27.
 * <p>
 * <p>
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，
 * 当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class EmptyReplace {


    public static String emptyReplace(String str) {
        if (str == null)
            return null;
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ')
                sb.append("%20");
            else
                sb.append(chars[i]);
        }

        return sb.toString();

    }

}
