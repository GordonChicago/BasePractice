package com.basepractice.util;

import android.util.Log;

/**
 * Created by admin on 2016/9/26.
 */

public final  class Tag {
    public final static String VIEW_TEST = "ViewTest";
    public static void i(String tag,String msg){
        Log.i(tag,msg);
    }
}
