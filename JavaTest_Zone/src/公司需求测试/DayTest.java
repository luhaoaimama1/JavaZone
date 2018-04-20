package 公司需求测试;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fuzhipeng on 2017/7/21.
 */
public class DayTest {

    public static void main(String[] args) {
        day("刚刚？",1L*9*60*1000);
        day("分钟前？", 1L*20*60*1000);
        day("小时前？", 1L*6*60*60*1000);
        day("天前？", 1L*29*24*60*60*1000);
        day("月日？", 1L*31*24*60*60*1000);
        day("年月日？", 1L*24*30*24*60*60*1000);
    }

    private static void day(String s, long i) {
        long  unixTime=new Date().getTime();
        System.out.println(s+fromToday2(new Date(unixTime-i)));
    }


    //    时间差 = 服务器返回时间 - 本地时间
//    刚刚：
//    时间差 <= 10分钟
//
//    x 分钟前：
//            10分钟 <= 时间差 < 60分钟
//
//    x 小时前：
//            1小时 <= 时间差 < 24小时
//
//    x 天前：
//            1天 <= 时间差 < 30天
//
//            显示日期
//30天 <= 时间差
//    日期格式：
//    当前年份：xx月xx日
//    其他年份：xxxx年xx月xx日
    public static String fromToday2(Date date) {

        if (date == null)
            return "";
        long delta = new Date().getTime() - date.getTime();

        if(delta <=1L*10*60*1000)//10分钟
            return "刚刚";

        if(delta <1L*60*60*1000)//60分钟
            return  delta / (1L*60*1000) + "分钟前";

        if(delta <1L*24*60*60*1000)//24小时
            return  delta / (1L*60*60*1000) + "小时前";

        if(delta < 1L*30*24*60*60*1000)//30天
            return  delta / (1L*24*60*60*1000) + "天前";

        Calendar calendarNow = Calendar.getInstance();
        Calendar calendarHistory = Calendar.getInstance();
        calendarHistory.setTime(date);
        if(calendarNow.get(Calendar.YEAR)==calendarHistory.get(Calendar.YEAR)){
            SimpleDateFormat time = new SimpleDateFormat("MM月dd日");
            return time.format(date);
        }else{
            SimpleDateFormat time = new SimpleDateFormat("yyyy年MM月dd日");
            return time.format(date);
        }
    }

}
