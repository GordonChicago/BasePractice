package com.basepractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.basepractice.util.Tag;

/**
 * Created by Administrator on 2016/10/27.
 */
public class MCustomViewGroup extends ViewGroup {
    public static final String TAG = MCustomViewGroup.class.getSimpleName();
    private Context mContext;

    public MCustomViewGroup(Context context) {
        this(context, null);
    }

    public MCustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MCustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        Tag.i(TAG, "onMeasure..."+getMeasuredWidth()+","+getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Tag.i(TAG, "onLayout...");

        int childCount = getChildCount();
        int sunHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int left = 0;
            int top = sunHeight;
            int right = left + childView.getMeasuredWidth();
            int bottom = top + childView.getMeasuredHeight();
            Tag.i(TAG,"left:"+left+",right:"+childView.getWidth());
            childView.layout(left,top,right,bottom);
            sunHeight += childView.getMeasuredHeight();
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
