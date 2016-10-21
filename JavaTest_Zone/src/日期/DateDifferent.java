package 日期;

import java.util.Calendar;

public class DateDifferent{
    public static void main(String[] args){
    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    calendar1.set(2007, 01, 10);
    calendar2.set(2007, 07, 01);
    long milliseconds1 = calendar1.getTimeInMillis();
    long milliseconds2 = calendar2.getTimeInMillis();

    long diff = milliseconds2 - milliseconds1;
    System.out.println("\nThe Date Different Example");
    System.out.println("Time in milliseconds: " + diffTime(milliseconds1,milliseconds2)[4] + " milliseconds.");
    System.out.println("Time in seconds: " + diffTime(milliseconds1,milliseconds2)[3] + " seconds.");
    System.out.println("Time in minutes: " + diffTime(milliseconds1,milliseconds2)[2] + " minutes.");
    System.out.println("Time in hours: " + diffTime(milliseconds1,milliseconds2)[1] + " hours.");
    System.out.println("Time in days: " + diffTime(milliseconds1,milliseconds2)[0] + " days.");
  }

    /**
     * @param timestamp1
     * @param timestamp2
     * @return new long[]{diffDays,diffHours,diffMinutes,diffSeconds,diff};
     */
  public static long[] diffTime(long timestamp1,long timestamp2){
      long diff = timestamp2 - timestamp1;
      long diffSeconds = Math.abs(diff / 1000);
      long diffMinutes =Math.abs( diff / (60 * 1000));
      long diffHours = Math.abs(diff / (60 * 60 * 1000));
      long diffDays = Math.abs(diff / (24 * 60 * 60 * 1000));
      return new long[]{diffDays,diffHours,diffMinutes,diffSeconds,diff};
  }

    public static void set(Calendar calendar,int field, int value) {
        calendar.set(field, value);

    }
    public static int get(Calendar calendar,int field) {
        if (Calendar.MONTH == field)
            return calendar.get(field) + 1;
        else
            return calendar.get(field);
    }

}