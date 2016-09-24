package com.basepractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.basepractice.util.ViewUtils;

/**
 * Created by Administrator on 2016/9/24.
 */
public class DrawTextView extends TextView {
    private int DEFAULT_WIDTH = 100; //数值为dp
    private int DEFAULT_HEIGHT = 100; //数值为dp

    public DrawTextView(Context context) {
        super(context);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ViewUtils.measureSize(getContext(),widthMeasureSpec,DEFAULT_WIDTH),
                ViewUtils.measureSize(getContext(),heightMeasureSpec,DEFAULT_HEIGHT));
    }
}
