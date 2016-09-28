package com.basepractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.basepractice.util.ViewUtils;

/**
 * Created by Administrator on 2016/9/26.
 */
public class DrawMusicView extends View {
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private final int DEFAULT_WIDTH = 200;
    private final int DEFAULT_HEIGHT = 200;
    public DrawMusicView(Context context) {
        this(context,null);
    }

    public DrawMusicView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ViewUtils.measureSize(getContext(),widthMeasureSpec,DEFAULT_WIDTH),
                ViewUtils.measureSize(getContext(),heightMeasureSpec,DEFAULT_HEIGHT));
    }
}
