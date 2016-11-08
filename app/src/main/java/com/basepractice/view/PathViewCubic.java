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
    public PathViewCubic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(8);

        mPath = new Path();
    }

    public PathViewCubic(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathViewCubic(Context context) {
        this(context,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);

        mPath.cubicTo(0,0,10,10,50,50);
        mPaint.setColor(Color.BLUE);

        canvas.drawPath(mPath,mPaint);
    }
}
