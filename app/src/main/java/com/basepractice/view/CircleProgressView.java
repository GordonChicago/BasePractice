package com.basepractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;

import com.basepractice.R;

/**
 * Created by admin on 2016/10/31.
 */

public class CircleProgressView extends ProgressBar {
    public static final float DEFAULT_REACH_WIDTH = 2;
    public static final float DEFAULT_UNREACH_WIDTH = 1;
    public static final int DEFAULT_REACH_COLOR = Color.RED;
    public static final int DEFAULT_UNREACH_COLOR = Color.BLUE;

    private Context mContext;
    private float mReachCircleWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_REACH_WIDTH, getContext().getResources().getDisplayMetrics());
    private float mUnReachCircleWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_UNREACH_WIDTH, getContext().getResources().getDisplayMetrics());
    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private Paint mPaint;
    private int mReachColor = DEFAULT_REACH_COLOR;
    private int mUnReachColor = DEFAULT_UNREACH_COLOR;
    private RectF mDrawArcLocal;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        mReachCircleWidth = typedArray.getDimension(R.styleable.CircleProgressView_reach_circle_width,mReachCircleWidth);
        mUnReachCircleWidth = typedArray.getDimension(R.styleable.CircleProgressView_unreach_circle_width,mUnReachCircleWidth);
        mReachColor = typedArray.getColor(R.styleable.CircleProgressView_reach_color,mReachColor);
        mUnReachColor = typedArray.getColor(R.styleable.CircleProgressView_unreach_color,mUnReachColor);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int actWidth = Math.min(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
        setMeasuredDimension(actWidth,actWidth);
        mCenterX = getWidth() / 2;
        mCenterY = getWidth() / 2;
        mRadius = getWidth() / 2;

        mDrawArcLocal = new RectF(0,0,getWidth(),getWidth());
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
//        mPaint.setColor(mUnReachColor);
//        mPaint.setStrokeWidth(mUnReachCircleWidth);
//        canvas.drawCircle(mCenterX,mCenterY,mRadius - mUnReachCircleWidth / 2,mPaint);
//        //计算角度
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachCircleWidth);
        mDrawArcLocal.left = mReachCircleWidth  / 2;
        mDrawArcLocal.top = mReachCircleWidth / 2;
        mDrawArcLocal.right = getWidth() - mReachCircleWidth /2;
        mDrawArcLocal.bottom = getWidth() - mReachCircleWidth /2;
        canvas.drawArc(mDrawArcLocal,0,sweepAngle,false,mPaint);
    }
}
