package com.basepractice.event.dispatch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.basepractice.util.MotionEventUtil;
import com.basepractice.util.Tag;

/**
 * Created by admin on 2016/9/28.
 */

public class MView1 extends View {
    private final String TAG = MView1.class.getSimpleName();
    public MView1(Context context) {
        super(context);
    }

    public MView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Tag.i(TAG,"dispatchTouchEvent ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Tag.i(TAG,"onTouchEvent ");
        if(MotionEventUtil.isACTION_DOWN(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }
}
