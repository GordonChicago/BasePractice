package com.basepractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/9/28.
 */
public class MScrollView extends ViewGroup {
    private int mScreenHeight;
    private float mLastY;
    public MScrollView(Context context) {
        this(context,null);
    }

    public MScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight;
        setLayoutParams(mlp);
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View child = getChildAt(i);
            if(child.getVisibility() != View.GONE) {
                child.layout(l,i*mScreenHeight,r,(i+1)*mScreenHeight);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for(int i=0;i<count;i++) {
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }
    private float mStart;
    private float mEnd;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStart=getScrollY();
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                float dScrollY = mEnd - mStart;
                if(dScrollY>0){
                    if(dScrollY<mScreenHeight/3){
                    }
                } else {
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }
}
