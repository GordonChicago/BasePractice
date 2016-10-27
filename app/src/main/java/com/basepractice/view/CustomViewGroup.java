package com.basepractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.basepractice.util.Tag;

/**
 * Created by admin on 2016/10/26.
 */

public class CustomViewGroup extends ViewGroup {
    final String TAG = CustomViewGroup.class.getSimpleName();
    Context mContext;

    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        Tag.i(TAG, mContext.getResources().getDisplayMetrics().density + "");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Tag.i(TAG, "onLayout..." + l + "," + t + "," + r + "," + b);
        int childCount = getChildCount();
        int sumViewHeight = 0;
        for (int i = 0; i < childCount; i++) {
            Tag.i(TAG,"index:"+i);
            View view = getChildAt(i);
            int viewWidth = view.getMeasuredWidth();
            int viewHeight = view.getMeasuredHeight();
            view.layout(l,t+sumViewHeight,viewWidth,t+sumViewHeight+viewHeight);
            sumViewHeight += viewHeight;
        }
    }
}
