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
                action = MotionType.ACTION_DOWN;
                break;
            case MotionEvent.ACTION_MOVE:
                action = MotionType.ACTION_MOVE;
                break;
            case MotionEvent.ACTION_UP:
                action = MotionType.ACTION_UP;
                break;
            default:
                action = MotionType.ACTION_UNKNOWN;
        }
        return action;
    }
    public static boolean isACTION_DOWN(MotionEvent event){
        boolean isDown = MotionType.ACTION_DOWN.equals(getActionString(event));
        return isDown;
    }
}
