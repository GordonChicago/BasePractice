package com.basepractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CanvasTestView extends View {
    private Context mContext;
    private Paint mPaint;

    public CanvasTestView(Context context) {
        this(context, null);
    }

    public CanvasTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //在整个画布上绘制颜色
        canvas.drawColor(Color.BLUE);

        int longLineWidth = 4;
        int shortLineWidth = 2;

        int longLineHeight = 50;
        int shortLineHeight = 25;

        int mLineLeft, mLineTop, mLineBottom;
        mLineTop = 50;

        for (int i = 0; i < 360; i++) {
            if (i % 30 == 0) {
                mPaint.setStrokeWidth(longLineWidth);
                mLineBottom = mLineTop + longLineHeight;
            } else {
                mPaint.setStrokeWidth(shortLineWidth);
                mLineBottom = mLineTop + shortLineHeight;
            }
            mLineLeft = getWidth() / 2 - (int) mPaint.getStrokeWidth() / 2;

            if (i % 6 == 0) {
                canvas.save();
                canvas.rotate(i, getWidth() / 2, getHeight() / 2);
                canvas.drawLine(mLineLeft, mLineTop, mLineLeft, mLineBottom, mPaint);
                canvas.restore();
            }
        }
    }
}
