package com.basepractice.util;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2016/9/24.
 */
public class ViewUtils {

    public static int measureSize(Context context,int sizeMeasureSpec, int defaultValueDP) {
        if (context == null) {
            return -1;
        }
        int mode = View.MeasureSpec.getMode(sizeMeasureSpec);
        int size = View.MeasureSpec.getSize(sizeMeasureSpec);
        int result = (int)dpToPx(context,defaultValueDP);
        if (mode == View.MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == View.MeasureSpec.AT_MOST){
            result = Math.min(result,size);
        }
        return result;
    }


    public static float dpToPx(Context context,int dpValue) {
        float px = 0;
        if(dpValue >=0 ){
            px = context.getResources().getDisplayMetrics().density * dpValue + 0.5f;
        }
        return px;
    }
}
