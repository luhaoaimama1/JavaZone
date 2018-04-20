package a面试;

import java.io.UnsupportedEncodingException;

/**
 * Created by fuzhipeng on 2017/3/12.
 * ，因为截取的字符字节长度必须小与数据库的字节长度，即如果最后一个字符为汉字，那么只能去掉往前截取。
 */
public class 中文字符截取2 {
    //todo 这个已经完成  一个汉子 对应两个字节，按块截取
    public static void main(String[] args) throws Exception {
        String str = "我s是fss汗";
        System.out.println(trimGBK(str, 20) + "");
//        我s是fss
    }

    //gbk编码解码后   byte 是负数 则是中文。 byte[]>0则是英文 和变电
    public static String trimGBK(String str, int byteCount) throws UnsupportedEncodingException {
        byte[] buf = str.getBytes("GBK");
        int num = 0;
        boolean bChineseFirestHalf = false;
        boolean chineseIsOver = true;
        for (int i = 0; i < buf.length; i++) {
            if (buf[i] < 0) {
                num++;
                chineseIsOver = !chineseIsOver;
            } else {
                num++;
            }
            if (num >= byteCount) {
                if (buf[i] > 0 || (buf[i] < 0 && chineseIsOver))
                    break;
            }
        }
        byte[] buf2 = new byte[num];
        for (int i = 0; i < buf2.length; i++) {
            buf2[i] = buf[i];
        }
        return new String(buf2,"GBK"); //根据substring按位数截取
    }

}
