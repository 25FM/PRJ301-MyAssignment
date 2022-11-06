/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.Week;

/**
 *
 * @author Manh
 */
public class DateTimeHelper {

    public static Date toDate(String value, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(value);
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static java.util.Date toDateUtil(java.sql.Date d) {
        java.util.Date x = new java.util.Date(d.getTime());
        x = removeTime(x);
        return x;
    }

    public static java.sql.Date toDateSql(java.util.Date d) {
        d = removeTime(d);
        return new java.sql.Date(d.getTime());
    }

    public static int getDayofWeek(java.util.Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    public static Date addDays(java.util.Date d, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static ArrayList<java.sql.Date>
            getDateList(java.sql.Date from, java.sql.Date to) {
        ArrayList<java.sql.Date> dates = new ArrayList<>();
        int days = 0;
        java.util.Date e_from = toDateUtil(from);
        java.util.Date e_to = toDateUtil(to);
        while (true) {
            java.util.Date d = DateTimeHelper.addDays(e_from, days);
            dates.add(toDateSql(d));
            days++;
            if (d.compareTo(e_to) >= 0) {
                break;
            }
        }
        return dates;
    }

    public static String getDayNameofWeek(java.sql.Date s) {
        java.util.Date d = toDateUtil(s);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thu";
            case 6:
                return "Fri";
            case 7:
                return "Sat";
        }
        return "Error";
    }

    public static int compare(java.sql.Date a, java.sql.Date b) {

        Date e_a = toDateUtil(a);
        Date e_b = toDateUtil(b);
        System.out.println(a + " " + b + " " + e_a.compareTo(e_b));
        return e_a.compareTo(e_b);
    }

    public static Week getWeekTime(int nth) {
        Calendar from = Calendar.getInstance();
        int dayOfWeek = from.get(Calendar.DAY_OF_WEEK);
        from.add(Calendar.DAY_OF_MONTH, -dayOfWeek + 2);
        int WeekOfYear = from.get(Calendar.WEEK_OF_YEAR);
        from.add(Calendar.WEEK_OF_YEAR, -WeekOfYear + nth + 1);
        Date fr = from.getTime();
        Calendar to = Calendar.getInstance();
        to.setTime(fr);
        to.add(Calendar.DATE, 6);
        Date dto = to.getTime();
        Week week = new Week(fr, dto);
        return week;
    }

    public static ArrayList<Week> getAllWeek() {
        ArrayList<Week> Weeks = new ArrayList<>();
        Week week;
        for (int i = 1; i <= 52; i++) {
            week = getWeekTime(i);
            Weeks.add(week);
        }
        return Weeks;
    }

    public static int getWeekOfYear(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        return calendar.get(Calendar.WEEK_OF_YEAR) - 1;

    }

    public static String format(Date day, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(day);
    }
    
    public static String getDayNameofWeek(Date day) {
       
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch(dayOfWeek)
        {
            case 1: return "Sun";
            case 2: return "Mon";
            case 3: return "Tue";
            case 4: return "Wed";
            case 5: return "Thu";
            case 6: return "Fri";
            case 7: return "Sat";
        }
        return "Error";
    }
    public static Date setTime(Date day, int slot){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        if(slot == 1){
            calendar.set(Calendar.HOUR_OF_DAY, 7 );
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
        }
        if(slot == 2){
            calendar.set(Calendar.HOUR_OF_DAY, 9 );
            calendar.set(Calendar.MINUTE, 10);
            calendar.set(Calendar.SECOND, 0);
        }
        if(slot == 3){
            calendar.set(Calendar.HOUR_OF_DAY, 10 );
            calendar.set(Calendar.MINUTE, 50);
            calendar.set(Calendar.SECOND, 0);
        }
        if(slot == 4){
            calendar.set(Calendar.HOUR_OF_DAY, 12 );
            calendar.set(Calendar.MINUTE, 50);
            calendar.set(Calendar.SECOND, 0);
        }
        if(slot == 5){
            calendar.set(Calendar.HOUR_OF_DAY, 14 );
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
        }
        if(slot == 6){
            calendar.set(Calendar.HOUR_OF_DAY, 16 );
            calendar.set(Calendar.MINUTE, 10);
            calendar.set(Calendar.SECOND, 0);
        }
        return calendar.getTime();
    }
    
    public static int compareToNowByDay(Date day){
        Date now = new Date();
        if(removeTime(now).compareTo(removeTime(day)) == 0){
            if(now.after(day))
                return 0;
        }
        return day.compareTo(now);
    }
}
