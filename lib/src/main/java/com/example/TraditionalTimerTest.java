package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2016/10/30.
 */

public class TraditionalTimerTest {
    static int i = 0;

    public static void main(String[] args) {
//        Timer timer1 = new Timer();
//
//        class MTimerTask extends TimerTask {
//
//            @Override
//            public void run() {
//                System.out.println("hallo");
//                i = (i + 1) % 2;
//                timer1.schedule(new MTimerTask(), i == 0 ? 2000 : 4000);
//            }
//        }
//
//        timer1.schedule(new MTimerTask(), 2000);

        String text = getTimeTextDiffTodayYear(System.currentTimeMillis());
        System.out.println(text);

    }

    public static String getTimeTextDiffTodayYear(long time) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        long todayZero = today.getTimeInMillis();
        if (time >= todayZero) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            return dateFormat.format(new Date(time));
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

    public static String getTimeTextDiffYear(long time) {
        Calendar todayYear = Calendar.getInstance();
        todayYear.set(Calendar.MONTH, 0);
        todayYear.set(Calendar.DAY_OF_MONTH, 0);
        todayYear.set(Calendar.HOUR_OF_DAY, 0);
        todayYear.set(Calendar.MINUTE, 0);
        todayYear.set(Calendar.SECOND, 0);
        todayYear.set(Calendar.MILLISECOND, 0);
        long todayYearZero = todayYear.getTimeInMillis();
        if (time >= todayYearZero) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
            return dateFormat.format(new Date(time));
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return dateFormat.format(new Date(time));
        }
    }
}
