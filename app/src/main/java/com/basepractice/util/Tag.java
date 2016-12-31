package com.basepractice.util;

/**
 * Created by admin on 2016/9/26.
 */

public final  class Tag {
    public final static String DEFAULT = "com.basepractice";
    public final static String VIEW_TEST = "ViewTest";
    public final static String LIST_VIEW = "LIST_VIEW";
    public final static String SELF_VIEW = "SelfView";
    public final static String EVENT_TAG = "Event_Tag";
    public final static boolean LOG_OFF = true;
    public static void i(String tag,String msg){
        Log.i(tag,msg);
    }
    public static void i(String msg){
        Log.i(DEFAULT,msg);
    }

    private static class Log {
        public static void i(String tag,String msg){
            if(LOG_OFF){
                android.util.Log.i(tag,msg);
            }
        }
    }
}
