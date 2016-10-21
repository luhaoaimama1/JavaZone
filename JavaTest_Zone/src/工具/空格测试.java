//package a未分类;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Created by fuzhipeng on 16/8/8.
// */
//public class 空格测试 {
//   public static String content="\n" +
//           "    //M系列\n" +
//           "    public static String M_Head_Url_Deba=\"\";//测试\n" +
//           "    public static String M_Head_Url_Debug=\"http://dev\\\".shenxian.\\\"com/mobile\";//测试\n" +
//           "    public static String M_Head_Url=\"https://m.shenxian.com\";//正式\n" +
//           "    //非M系列\n" +
//           "    public static String Head_Url_Debug=\" http://dev.she  nxian.com/app\";//测试\n" +
//           "    public static String Head_Url=\"https://app.shenxian.com\";//正式";
//    public static void main(String[] args) {
//        Pattern p = Pattern.compile("\".*\"");
//        Pattern pS = Pattern.compile("\\s+");
//        Matcher m = p.matcher(content);
//        while (m.find()){
//            for (int i = 0; i <= m.groupCount(); i++) {
//                String res = m.group(i);
//
//                Matcher mS=pS.matcher(res);
//                while(mS.find()){
//                    for (int i1 = 0; i1 < mS.groupCount(); i1++) {
//                        String resSSS = m.group(i);
//                        System.out.println("空格---->"+resSSS+"\t 开始:"+ mS.start(i)+"\t 结束:"+ mS.end(i));
//                    }
//                }
//
//                System.out.println(res+"\t 开始:"+ m.start(i)+"\t 结束:"+ m.end(i));
////                if (res == null || i == 0) {
////                    continue;
////                }
//            }
//        }
//
//    }
//
//    //M系列
//    public static String M_Head_Url_Deba="";//测试
//    public static String M_Head_Url_Debug="http://dev\".shenxian.\"com/mobile";//测试
//    public static String M_Head_Url="https://m.shenxian.com";//正式
//    //非M系列
//    public static String Head_Url_Debug="http://dev.shenxian.com/app";//测试
//    public static String Head_Url="https://app.shenxian.com";//正式
//}
