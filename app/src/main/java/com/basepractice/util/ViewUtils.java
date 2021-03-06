package com.basepractice.util;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2016/9/24.
 */
public class ViewUtils {

    public static int measureSize(Context context, int sizeMeasureSpec, int defaultValueDP) {
        if (context == null) {
            return -1;
        }
        int mode = View.MeasureSpec.getMode(sizeMeasureSpec);
        int size = View.MeasureSpec.getSize(sizeMeasureSpec);
        int result = (int) dpToPx(context, defaultValueDP);
        if (mode == View.MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == View.MeasureSpec.AT_MOST) {
            result = Math.min(result, size);
        }
        return result;
    }

    public static String getMeasureSpecMode(int measureSpecValue) {
        String strMode = "";
        int mode = View.MeasureSpec.getMode(measureSpecValue);
        if (mode == View.MeasureSpec.AT_MOST){
            strMode = "AT_MOST";
        } else if (mode == View.MeasureSpec.EXACTLY) {
            strMode = "EXACTLY";
        } else if (mode == View.MeasureSpec.UNSPECIFIED) {
            strMode = "UNSPECIFIED";
        } else {
            strMode = "UNKNOWN";
        }
        return strMode;
    }


    public static float dpToPx(Context context, int dpValue) {
        float px = 0;
        if (dpValue >= 0) {
            px = context.getResources().getDisplayMetrics().density * dpValue + 0.5f;
        }
        return px;
    }

    public static float pxTodp(Context context, int pxValue) {
        float dp = (pxValue + 0.5f) / context.getResources().getDisplayMetrics().density;
        return dp;
    }

    public static float spToPx(Context context, int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * fontScale + 0.5f;
    }
}
