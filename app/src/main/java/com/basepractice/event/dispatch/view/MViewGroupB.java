package com.basepractice.event.dispatch.view;

import android.content.Context;
import android.graphics.LinearGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.basepractice.util.MotionEventUtil;
import com.basepractice.util.Tag;

/**
 * Created by admin on 2016/9/28.
 */

public class MViewGroupB extends LinearLayout {
    private final String TAG = MViewGroupB.class.getSimpleName();
    public MViewGroupB(Context context) {
        super(context);
    }

    public MViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Tag.i(TAG,"dispatchTouchEvent " + MotionEventUtil.getActionString(ev));
        super.dispatchTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Tag.i(TAG,"onInterceptTouchEvent " + MotionEventUtil.getActionString(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Tag.i(TAG,"onTouchEvent " + MotionEventUtil.getActionString(event));
        return super.onTouchEvent(event);
    }
}
