package 日期;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by fuzhipeng on 2016/10/21.
 */

public class CalendarWrap extends GregorianCalendar {


    public static void main(String[] args) {
        //创建动态代理对象
        Calendar k = Calendar.getInstance();
//        set(k, Calendar.MONTH, 9);
        System.out.println("月份:" + get(k, Calendar.MONTH));
    }

    public static void set(Calendar calendar, int year, int month, int date, int hourOfDay, int minute,
                           int second) {
        calendar.set(YEAR, year);
        calendar.set(MONTH, month);
        calendar.set(DATE, date);
        calendar.set(HOUR_OF_DAY, hourOfDay);
        calendar.set(MINUTE, minute);
        calendar.set(SECOND, second);
    }

    public static void set(Calendar calendar, int year, int month, int date) {
        calendar.set(YEAR, year);
        calendar.set(MONTH, month);
        calendar.set(DATE, date);
    }

    public static void set(Calendar calendar, int year, int month, int date, int hourOfDay, int minute) {
        calendar.set(YEAR, year);
        calendar.set(MONTH, month);
        calendar.set(DATE, date);
        calendar.set(HOUR_OF_DAY, hourOfDay);
        calendar.set(MINUTE, minute);
    }

    public static void set(Calendar calendar, int field, int value) {
        if (Calendar.MONTH == field) {
            calendar.set(field, value - 1);
        } else
            calendar.set(field, value);

    }

    public static int get(Calendar calendar, int field) {
        if (Calendar.MONTH == field)
            return calendar.get(field) + 1;
        else
            return calendar.get(field);
    }
}
