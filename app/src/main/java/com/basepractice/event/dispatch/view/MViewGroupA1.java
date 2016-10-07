package com.basepractice.event.dispatch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.basepractice.util.Tag;

/**
 * Created by admin on 2016/9/28.
 */

public class MViewGroupA1 extends LinearLayout{
    private final String TAG = MViewGroupA1.class.getSimpleName();
    public MViewGroupA1(Context context) {
        super(context);
    }

    public MViewGroupA1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MViewGroupA1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Tag.i(TAG,"dispatchTouchEvent" );
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Tag.i(TAG,"onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Tag.i(TAG,"onTouchEvent");
        return super.onTouchEvent(event);
    }
}
