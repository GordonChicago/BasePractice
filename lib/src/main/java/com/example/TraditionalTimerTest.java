package com.example;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2016/10/30.
 */

public class TraditionalTimerTest {
    static int i = 0;

    public static void main(String[] args) {
        Timer timer1 = new Timer();

        class MTimerTask extends TimerTask {

            @Override
            public void run() {
                System.out.println("hallo");
                i = (i + 1) % 2;
                timer1.schedule(new MTimerTask(), i == 0 ? 2000 : 4000);
            }
        }

        timer1.schedule(new MTimerTask(), 2000);
    }
}
