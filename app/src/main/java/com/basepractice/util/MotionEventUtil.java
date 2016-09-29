package com.basepractice.util;

import android.view.MotionEvent;

/**
 * Created by admin on 2016/9/29.
 */

public class MotionEventUtil {
    public static String getActionString(MotionEvent event) {
        String action = "";
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                break;
            default:
                action = "UNKNOWN";
        }
        return action;
    }
}
