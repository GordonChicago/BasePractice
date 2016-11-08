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
public class CanvasTestViewTwo extends View {
    private Context mContext;
    private Paint mPaint;

    public CanvasTestViewTwo(Context context) {
        this(context, null);
    }

    public CanvasTestViewTwo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasTestViewTwo(Context context, AttributeSet attrs, int defStyleAttr) {
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

        Rect rect = new Rect(0, 0, getWidth() / 4, getHeight() / 4);

        mPaint.setColor(Color.RED);
        canvas.drawRect(rect, mPaint);

        //save保持画布状态
        canvas.save();

        //translate
        mPaint.setColor(Color.YELLOW);
        canvas.translate(getWidth() / 4, getHeight() / 4);
        canvas.drawRect(rect, mPaint);

        //restore 回滚到save的画布状态
        canvas.restore();

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rect, mPaint);

        canvas.save();
        //scale
        canvas.translate(getWidth() / 4, 0);
        canvas.scale(0.5f, 0.5f, getWidth() / 4, getHeight() / 4);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rect, mPaint);
        canvas.restore();

        canvas.save();
        //rotate
        canvas.rotate(45, getWidth() / 4, getHeight() / 4);
        mPaint.setColor(Color.DKGRAY);
        canvas.drawRect(rect, mPaint);
        canvas.restore();
    }
}
