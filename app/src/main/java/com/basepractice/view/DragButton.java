package com.basepractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Scroller;

/**
 * 实现滑动的方法：
 *  1.计算偏移量,使用layout进行重新布局
 *      layout(getLeft()+distanceX,getTop()+distanceY,getRight()+distanceX,getBottom()+distanceY);
 *  2.计算偏移量,使用offsetLeftAndRight和offsetTopAndBottom进行上下偏移
 *      offsetLeftAndRight(distanceX);
 *      offsetLeftAndRight(distanceY);
 */
public class DragButton extends Button {
    private float mLastX;
    private float mLastY;

    private float mMoveX;
    private float mMoveY;

    private Scroller mScroller;
    public DragButton(Context context) {
        this(context,null);
    }

    public DragButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = event.getX();
                mMoveY = event.getY();
                int distanceX = (int)(mMoveX - mLastX);
                int distanceY = (int)(mMoveY - mLastY);


                //第一种实现滑动的方法
                layout(getLeft()+distanceX,getTop()+distanceY,getRight()+distanceX,getBottom()+distanceY);

                //第二种实现滑动的方法
//                offsetLeftAndRight(distanceX);
//                offsetTopAndBottom(distanceY);

                //第三种实现滑动的方法
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + distanceX;
//                layoutParams.topMargin = getTop() + distanceY;
//                setLayoutParams(layoutParams);

                //第四种移动父空间
//                ((ViewGroup)getParent()).scrollBy(-distanceX,-distanceY);

                //第五种方法通过Scroller

                //第六中方法使用属性动画

                //第七中方法使用ViewDragHelper

                mLastX = mMoveX;
                mLastY = mMoveY;
                break;
            case MotionEvent.ACTION_UP:

//                ViewGroup parent = (ViewGroup)getParent();
//                mScroller.startScroll(parent.getScrollX(),parent.getScrollY(),-parent.getScrollX(),-parent.getScrollY(),2000);
//                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ViewGroup parent = (ViewGroup)getParent();
            parent.scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }
}
