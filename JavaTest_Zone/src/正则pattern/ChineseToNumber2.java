package 正则pattern;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fuzhipeng on 16/9/21.
 */

public class ChineseToNumber2 {

    public static void main(String[] args) {
        for (SplitEntity splitEntity : splitStr2) {
            System.out.println("KKK=" + splitEntity.SplitInteger);
        }

        String test1 = "三百六十五亿一千三百6十五万2千3百2十2.五毛6分";
//        test1 = "1990万";
//        test1 = "1990";
//        test1 = "1990万一毛";
//        test1 = "6十五万2千3百2十2.五毛6分";
//        test1 = "1658毛";
//        test1 = "1658块1毛";
//        test1 = "1691块";

        System.out.println("嘿嘿:" + new BigDecimal(parseIntegerPart(test1)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());


    }

    //1-------1万2千3百2十2块五毛6分;
    //2-------1万2千3百2十2.5毛6分;
    //3-------1123点665
    //first:碰到 baseStr 编程  0123456
    //second:碰到断位符号 换成然后+;
    //全局数组,存储单位量级

    private static List<SplitEntity> splitStr2 = new ArrayList<>();
    private static List<SplitEntity> splitStr = new ArrayList<>();
    private static Map<String, String> baseStr = new HashMap<>();

    static {
        splitStr2.add(new SplitEntity("分", (double) Math.pow(10, -2)));
        splitStr2.add(new SplitEntity("毛", (double) Math.pow(10, -1)));
        splitStr2.add(new SplitEntity("元", (double) Math.pow(10, 0)));
        splitStr2.add(new SplitEntity("十", (double) Math.pow(10, 1)));
        splitStr2.add(new SplitEntity("百", (double) Math.pow(10, 2)));
        splitStr2.add(new SplitEntity("千", (double) Math.pow(10, 3)));

        splitStr.add(new SplitEntity("万", (double) Math.pow(10, 5)));
        splitStr.add(new SplitEntity("亿", (double) Math.pow(10, 9)));

        baseStr.put("零", "0");
        baseStr.put("一", "1");
        baseStr.put("二", "2");
        baseStr.put("三", "3");
        baseStr.put("四", "4");
        baseStr.put("五", "5");
        baseStr.put("六", "6");
        baseStr.put("七", "7");
        baseStr.put("八", "8");
        baseStr.put("九", "9");
        baseStr.put("块", "元");
        baseStr.put(".", "元");
    }


    public static Double parseIntegerPart(String str) {

        if (isNumber(str))
            return Double.parseDouble(str);
        String test2 = replaceBase(str);

        System.out.println("test:" + test2);


        Map<String, String> splitResult = getSplit1(test2);

        double resultFinal = 0;
        for (Map.Entry<String, String> stringStringEntry : splitResult.entrySet()) {
            Map<String, String> temp = getSplit2(stringStringEntry.getValue());
            double result = 0;
            for (Map.Entry<String, String> stringEntry : temp.entrySet()) {
                result += Integer.parseInt(stringEntry.getValue()) * getSplitEntity2(stringEntry.getKey()).SplitInteger;
            }
            try {
                resultFinal += result * getSplitEntity(stringStringEntry.getKey()).SplitInteger;
            } catch (Exception e) {
                resultFinal += result;
            }

        }
        System.out.println("splitResult:" + resultFinal);
        System.out.println("splitResult:" + new BigDecimal(resultFinal).setScale(2, BigDecimal.ROUND_HALF_UP));
        return resultFinal;
    }


    private static SplitEntity getSplitEntity(String key) {
        for (SplitEntity splitEntity : splitStr) {
            if (splitEntity.SplitStr.equals(key))
                return splitEntity;
        }
        return null;
    }

    private static SplitEntity getSplitEntity2(String key) {
        for (SplitEntity splitEntity : splitStr2) {
            if (splitEntity.SplitStr.equals(key))
                return splitEntity;
        }
        return null;
    }

    private static Map<String, String> getSplit2(String test2) {
        Map<String, String> splitResult = new HashMap<>();
        int lastIndex = -1;
        for (int i = splitStr2.size() - 1; i >= 0; i--) {

            String temp = splitStr2.get(i).SplitStr;
            if (!test2.contains(temp))
                continue;
            String[] splitTemp = test2.split(temp);
            if (splitTemp.length > 1) {
                test2 = splitTemp[1];
                lastIndex = i;
                splitResult.put(splitStr2.get(i).SplitStr, splitTemp[0]);
            }else{
                //1658毛  应该本分到 毛
                lastIndex = i+1;
            }
        }
        if (lastIndex == -1)
            //元
            splitResult.put(splitStr2.get(2).SplitStr, isNumber(test2) ? test2 : test2.substring(0, test2.length() - 1));
        else if (lastIndex == 0)
            splitResult.put(splitStr2.get(0).SplitStr, isNumber(test2) ? test2 : test2.substring(0, test2.length() - 1));
        else if (lastIndex > 0)
            splitResult.put(splitStr2.get(lastIndex-1).SplitStr, isNumber(test2) ? test2 : test2.substring(0, test2.length() - 1));
        System.out.println("毛线:" + splitResult);
        return splitResult;
    }

    private static boolean isNumber(String test) {
        try {
            Double.parseDouble(test);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private static Map<String, String> getSplit1(String test2) {
        Map<String, String> splitResult = new HashMap<>();
        int lastIndex = -1;
        for (int i = splitStr.size() - 1; i >= 0; i--) {
            String temp = splitStr.get(i).SplitStr;
            if (!test2.contains(temp))
                continue;
            String[] splitTemp = test2.split(temp);
            if (splitTemp.length > 1) {
                test2 = splitTemp[1];
                splitResult.put(splitStr.get(i).SplitStr, splitTemp[0]);
                lastIndex = i;
            }else{
                lastIndex = i+1;
            }
        }

        if (lastIndex == -1 || lastIndex == 0)
            splitResult.put("万以下", test2);
        else if (lastIndex > 0)
            splitResult.put(splitStr.get(lastIndex - 1).SplitStr, isNumber(test2) ? test2 : test2.substring(0, test2.length() - 1));


        System.out.println(splitResult);
        return splitResult;
    }

    private static String replaceBase(String test1) {
        for (Map.Entry<String, String> stringStringEntry : baseStr.entrySet()) {
            test1 = test1.replace(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        return test1;
    }

    public static class SplitEntity {
        public String SplitStr;
        public Double SplitInteger;

        public SplitEntity(String splitStr, Double splitInteger) {
            SplitStr = splitStr;
            SplitInteger = splitInteger;
        }
    }
}
