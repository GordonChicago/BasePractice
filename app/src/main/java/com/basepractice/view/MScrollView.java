package com.basepractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.ViewUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.basepractice.util.Tag;

/**
 * Created by Administrator on 2016/9/28.
 */
public class MScrollView extends ViewGroup {
    private int mScreenHeight;
    private float mLastY;
    private float mStart;
    private float mEnd;
    private Scroller mScroller;

    public MScrollView(Context context) {
        this(context, null);
    }

    public MScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
        mScroller = new Scroller(context);
        Log.i(Tag.VIEW_TEST,"MScrollView");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(Tag.VIEW_TEST,"onLayout");
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight * getChildCount();
        setLayoutParams(mlp);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(Tag.VIEW_TEST,"onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(Tag.VIEW_TEST,"onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = event.getY();
                mStart = getScrollY();
                Log.i(Tag.VIEW_TEST, "scrollView -- ACTION_DOWN :" + mLastY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float dy = mLastY - event.getY();
                scrollBy(0, (int) dy);
                mLastY = event.getY();
                if (getScrollY() <= 0) {
                    scrollTo(0, 0);
                    break;
                } else if (getScrollY() >(getHeight() - mScreenHeight)) {
                    scrollTo(0,getHeight()-mScreenHeight);
                    break;
                }
                Log.i(Tag.VIEW_TEST, "scrollView -- ACTION_MOVE : dy=" + dy);
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = (int)(mEnd - mStart);
                if(dScrollY > 0) {
                    if(dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    } else {
                        mScroller.startScroll(0,getScrollY(),0,mScreenHeight - dScrollY);
                    }
                } else {
                    //也就是在拉动的情况下，向下拉
                    dScrollY = (int)(mStart - mEnd);
                    if(dScrollY < mScreenHeight /3) {
                        mScroller.startScroll(0,getScrollY(),0,dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-mScreenHeight+dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        Log.i(Tag.VIEW_TEST,"computeScroll");
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(Tag.VIEW_TEST,"onSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(Tag.VIEW_TEST,"onSizeChanged");
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i(Tag.VIEW_TEST,"dispatchDraw");
        super.dispatchDraw(canvas);
    }
}
