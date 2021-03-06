package com.basepractice.event.dispatch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.basepractice.util.MotionEventUtil;
import com.basepractice.util.Tag;

/**
 * Created by admin on 2016/9/28.
 */

public class MView extends View {
    private final String TAG = Tag.EVENT_TAG+"View--";
    public MView(Context context) {
        super(context);
    }

    public MView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MView(Context context, AttributeSet attrs, int defStyleAttr) {
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
//        if(MotionEventUtil.isACTION_DOWN(event)){
//            Tag.i(TAG,"aaaaaaaaaaaaaaaaaa------------");
//            return true;
//        }
        return super.onTouchEvent(event);
    }
}
