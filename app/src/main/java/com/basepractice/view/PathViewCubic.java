package com.basepractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/2.
 */
public class PathViewCubic extends View {
    private Paint mPaint;
    private Path mPath;

    public PathViewCubic(Context context) {
        this(context,null);
    }

    public PathViewCubic(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathViewCubic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GREEN);

//        mPath.moveTo(100,100);
//        mPath.lineTo(200,200);

//        mPath.moveTo(100,50);
//        mPath.quadTo(200,100,300,300);

        mPath.moveTo(0,0);
        mPath.cubicTo(100,400,200,0,400,400);

        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath,mPaint);
    }
}
