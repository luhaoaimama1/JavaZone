package 工具;

/**
 * Created by fuzhipeng on 16/9/7.
 */
public class ColorParse2 {

    //    69 75 89, 0.90
//    69 75 89
    public static void main(String[] args) throws Exception {
//        System.out.println(Integer.toHexString(-14606047));
        System.out.println(getValue("ff"));
        System.out.println(getValue("CC"));
        System.out.println(getValue("bf"));
        System.out.println(getValue("b0"));
    }

    private static float getValue(String cc) {
        System.out.print(cc + "==>" + Integer.parseInt(cc, 16) + " 百分比:");
        return Integer.parseInt(cc, 16)*1f / 255;
    }

}
