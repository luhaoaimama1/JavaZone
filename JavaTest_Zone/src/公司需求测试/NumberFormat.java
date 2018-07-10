
package 公司需求测试;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * 数字规范
 * 1）数字为空
 * 显示-，若有特殊业务要求，可根据业务要求展现
 * 2）数字为0
 * 显示0
 * 3）数字为0-9999之间的四位数
 * 显示全部数字，不四舍五入，不截断，不隐藏
 * 4）数字为10000以上的五位数
 * 单位显示万，精确到小数点后一位，四舍五入到千位，显示格式：X.Y万
 * 若Y不等于0时，全部显示，如3.5万
 * 若Y等于0时，不显示小数点和小数点后数字，如3万
 * 5）数字为大于99999以上的六位数
 * 按照需求4的格式显示
 * 6）数字为大于等于一亿以上的数字
 * 单位显示亿，精确到小数点后一位，四舍五入到千万位，显示格式：X.Y亿
 * 若Y不等于0时，全部显示，如3.5亿
 * 若Y等于0时，不显示小数点和小数点后数字，如3亿
 * 7 )加载中时，展示原来的数字，加载成功后刷新。加载失败或取不到数，则展示“-”
 * <p>
 * 附件
 * 添加
 *
 * @author Zone
 */
public final class NumberFormat {

    private static final int MILLION = 100000000;
    private static final int TEN_THOUSAND = 10000;//1万

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE>MILLION);
        Long[] arrays = new Long[]{
                3723L,
                7095009L,
                19999L,
                19444L,
                109500L,
                709500L,
                809500L,
                909500L,
                199500000L,
                1149500000L,
                129500000L
        };
        for (Long array : arrays) {
            System.out.println(array + "->" + format(array, "默认值"));
        }

    }

    public static String format(int num, String defValue) {
        return format((long) num, defValue);
    }

    public static String format(String numberString, String delValue) {
        try {
            Long number = Long.parseLong(numberString);
            return format(number, delValue);
        } catch (NumberFormatException ignored) {
        }
        return delValue;
    }

    public static String format(long num, String defValue) {
        if (num == 0)
            return defValue;

        if (num < TEN_THOUSAND) {//万以下
            return num + "";
        } else if (num < MILLION) {//万以上
            return getString(num, 4, "万");
        } else {//亿以上
            return getString(num, 8, "亿");
        }

    }

    /**
     * @param num
     * @param limit 10的次方
     * @param end
     * @return
     */
    private static String getString(long num, long limit, String end) {
        long beforeLimt = limit - 1;
        double roundResult = Math.round(num * 1F / Math.pow(10, beforeLimt)) * Math.pow(10, beforeLimt);
//        System.out.println("roundResult:" + roundResult);
        double roundResult2 = (roundResult * 1L) / Math.pow(10, limit);
        if ((roundResult * 1L) % Math.pow(10, limit) == 0) {
            return numberDotFormat(roundResult2, 0) + end;
        } else {
            return numberDotFormat(roundResult2, 1) + end;
        }
    }

    /**
     * @param number
     * @param dotNumber 例如1.1111 当0显示1  3显示1.111   10则显示1.1111 即超出没事的
     * @return
     */
    public static String numberDotFormat(double number, int dotNumber) {
        StringBuilder sb = new StringBuilder();
        if (dotNumber == 0) {
            sb.append("#");
        } else {
            sb.append("#.");
            for (int i = 0; i < dotNumber; i++) {
                sb.append("#");
            }
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        return df.format(number);
    }

}
