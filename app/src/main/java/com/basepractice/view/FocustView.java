package com.basepractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2016/10/18.
 */

public class FocustView extends View {
    private static final String TAG = FocustView.class.getSimpleName();
    public FocustView(Context context) {
        super(context);
    }

    public FocustView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocustView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                showLog("ACTION_DOWN");
                if(true){
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                showLog("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                showLog("ACTION_UP");
                break;
        }

        return super.onTouchEvent(event);
    }

    private void showLog(String msg) {
        Log.i(TAG,msg);
    }
}
