package com.basepractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by admin on 2016/10/18.
 */

public class FocusButton extends Button {
    private Context mContext;
    private View mView;
    private float mLastX,mLastY;
    public FocusButton(Context context) {
        this(context,null);
    }

    public FocusButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FocusButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float nowX = event.getX();
                float nowY = event.getY();
                int changeInX = (int)(nowX - mLastX);
                int changeInY = (int)(nowY - mLastY);
                mView.layout(mView.getLeft() + changeInX,mView.getTop() + changeInY,mView.getRight() + changeInX,mView.getBottom() + changeInY);
                mLastX = nowX;
                mLastY = nowY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            mView.setVisibility(VISIBLE);
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void setShowView(View view) {
        this.mView = view;
    }
}
