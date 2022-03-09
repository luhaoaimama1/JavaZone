package a_b_改善;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class H5RandUUIDUtils {
    private static long uniqueId() {
        long val = 0;
        while (val == 0) {
            val = ThreadLocalRandom.current().nextLong();
        }
        return val;
    }

    private static String to16HexString(long id) {
        final String hex = Long.toHexString(id);
        if (hex.length() == 16) {
            return hex;
        }
        return "0000000000000000".substring(hex.length()) + hex;
    }

    public static String convertTraceId() {
        long traceIdLow = uniqueId();
        long traceIdHigh = uniqueId();
        final String hexStringHigh = to16HexString(traceIdHigh);
        final String hexStringLow = to16HexString(traceIdLow);
        return hexStringHigh + hexStringLow;
    }


    public static void main(String[] args) {
        System.out.println( H5RandUUIDUtils.convertTraceId());
//        ArrayList<String> stringArrayList = new ArrayList<>();
//        stringArrayList.add("123");
//        if(stringArrayList.indexOf("123")!=-1){
//            System.out.println("you：");
//            return;
//        }
//
//        for (int i = 0; i < 10000; i++) {
//            String s = H5RandUUIDUtils.convertTraceId();
//            if (stringArrayList.indexOf(s) != -1) {
//                System.out.println("重复：" + s);
//                return;
//            } else {
//                System.out.println("插入："+s);
//                stringArrayList.add(s);
//            }
//        }
//        System.out.println("成功呢！");
    }
}