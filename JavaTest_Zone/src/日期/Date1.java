package 日期;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fuzhipeng on 2016/10/20.
 */
public class Date1 {
    public static void main(String[] args) {
        System.out.println("1:"+getWeekOfDate2(new Date()));
        System.out.println("2:"+getWeekOfDate(new Date()));
    }

    /**
     * 获取当前日期是星期几<br>
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    /**
     * 获取当前日期是星期几<br>
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate2(Date dt) {
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        return dateFm.format(dt);
    }
}
