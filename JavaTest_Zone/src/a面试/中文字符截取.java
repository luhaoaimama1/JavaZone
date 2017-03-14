package a面试;

import java.io.UnsupportedEncodingException;

/**
 * Created by fuzhipeng on 2017/3/12.
 * ，因为截取的字符字节长度必须小与数据库的字节长度，即如果最后一个字符为汉字，那么只能去掉往前截取。
 */
public class 中文字符截取 {
    public static void main(String[] args) throws Exception {
        String str = "我ss是fs汗";
        System.out.println(trimGBK(str, 4)+"");

        String s = "a加b等于c，如果a等1、b等于2，那么c等3";
        System.out.println(subStringChinese(s, 6));
    }

    //gbk编码解码后   byte 是负数 则是中文。 byte[]>0则是英文 和变电
    public static String trimGBK(String str, int n) throws UnsupportedEncodingException {
        byte[] buf = str.getBytes("GBK");
        int num = 0;
        boolean bChineseFirestHalf = false;
        for (int i = 0; i < n; i++) {
            if (buf[i] < 0 && !bChineseFirestHalf) {
                bChineseFirestHalf = true;
            } else {
                num++;
                bChineseFirestHalf = false;
            }
        }
        return str.substring(0, num); //根据substring按位数截取
    }

    /**
     * Unicode 解码后。
     * 由于上面生成的字节数组中前两个字节是标志位，bytes[0] = -2，bytes[1] = -1，
     * 因此，要从第三个字节开始扫描，对于一个英文或数字字符，UCS2编码的第二个字节是相应的ASCII，
     * 第一个字节是0，如a的UCS2编码是0  97，而汉字两个字节都不为0，因此，可以利于UCS2编码的这个规则来计算实际的字节数，该方法的实现代码如下：
     */
    public static String subStringChinese(String s, int length) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("Unicode");
        int i = 2; // 要截取的字节数，从第3个字节开始
        int n = 0; // 表示当前的字节数  当达到 length 即可跳出 for的循环。
        while (i < bytes.length && n < length) {
            i++;
            if (bytes[i] != 0) {
                //中文
            } else {//数字或者英文
            }
            n++;
            i++;
        }
        return new String(bytes, 0, i, "Unicode");

    }
}
