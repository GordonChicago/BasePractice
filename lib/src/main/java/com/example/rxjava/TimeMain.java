package com.example.rxjava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/9.
 */

public class TimeMain {
    public static void main(String[] args) {
        long t = 1474797581291l;
        System.out.println("tt;"+getTimeTextNowToday(t));
        Date date = new Date(t);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("sdf:"+sdf.format(date));
    }

    public static String getTimeTextNowToday(long time) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        long todayZero = today.getTimeInMillis();

        if (time >= todayZero) {
            return "今天";
        }

        Calendar yesterday = Calendar.getInstance();
        yesterday.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);
        yesterday.set(Calendar.MILLISECOND, 0);
        long yesterdayZero = yesterday.getTimeInMillis();

        if (time >= yesterdayZero && time < todayZero) {
            return "昨天";
        }

        Calendar todayYear = Calendar.getInstance();
        todayYear.set(Calendar.MONTH, 0);
        todayYear.set(Calendar.DAY_OF_MONTH, 0);
        todayYear.set(Calendar.HOUR_OF_DAY, 0);
        todayYear.set(Calendar.MINUTE, 0);
        todayYear.set(Calendar.SECOND, 0);
        todayYear.set(Calendar.MILLISECOND, 0);
        long todayYearZero = todayYear.getTimeInMillis();

        if (time >= todayYearZero) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
            return dateFormat.format(new Date(time));
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(new Date(time));
        }
    }
}
