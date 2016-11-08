package com.basepractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Path.Direction.CW，顺时针
 * Path.Direction.CCW,逆时针
 */
public class PathView extends View {
    private Context mContext;
    private Paint mPaint;
    private Path mPath;
    private Path mPathSrc;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        mPath = new Path();
        mPathSrc = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        mPath.addCircle(getWidth() / 2, getHeight() / 2, getWidth() / 4, Path.Direction.CCW);
        mPath.addRect(getWidth() / 8, getHeight() / 2, getWidth() / 3, getHeight() / 2 + getWidth() / 4, Path.Direction.CW);

        mPaint.setColor(Color.GREEN);
        mPath.setFillType(Path.FillType.WINDING);

        canvas.drawPath(mPath, mPaint);

    }
}
