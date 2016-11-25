package com.basepractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.basepractice.R;

/**
 * 中间文字的大小，用户自己手动适配
 */
public class CircleProgressBar extends View {
    public static int DEFAULT_TEXT_SIZE = 20;
    public static int DEFAULT_TEXT_COLOR = Color.WHITE;
    public static int DEFAULT_OVAL_RADIUS = 50;
    public static int DEFAULT_OVAL_COLOR = Color.BLUE;
    public static int DEFAULT_CIRCLE_WIDTH = 2;
    public static int DEFAULT_CIRCLE_COLOR = Color.WHITE;
    public static String DEFAULT_TEXT = "跳过";

    //文本定义
    private float mTextSize = DEFAULT_TEXT_SIZE;
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private String mText = DEFAULT_TEXT;

    //中间圆的颜色和半径
    private float mOvalRadius = DEFAULT_OVAL_RADIUS;
    private int mOvalColor = DEFAULT_OVAL_COLOR;

    //外面圈的颜色和粗度
    private float mCircleWidth = DEFAULT_CIRCLE_WIDTH;
    private int mCircleColor = DEFAULT_CIRCLE_COLOR;

    //系统变量
    private Context mContext;

    private float mMinWidth;

    private RectF mCircleArea;
    private RectF mOvalArea;

    private Paint mPaint;

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        TypedArray typedArray = mContext.getResources().obtainAttributes(attrs, R.styleable.CircleProgressBar);
        //文字属性
        mText = typedArray.getString(R.styleable.CircleProgressBar_text);
        mTextSize = typedArray.getDimension(R.styleable.CircleProgressBar_text_size, mTextSize);
        mTextColor = typedArray.getColor(R.styleable.CircleProgressBar_textColor, mTextColor);
        //圆属性
        mOvalRadius = typedArray.getDimension(R.styleable.CircleProgressBar_oval_radius, mOvalRadius);
        mOvalColor = typedArray.getColor(R.styleable.CircleProgressBar_oval_color, mOvalColor);
        //外圆圈
        mCircleWidth = typedArray.getDimension(R.styleable.CircleProgressBar_circle_width, mCircleWidth);
        mCircleColor = typedArray.getColor(R.styleable.CircleProgressBar_circle_color, mCircleColor);
        typedArray.recycle();

        mMinWidth = (mCircleWidth + mOvalRadius) * 2;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        float actWidth = widthSize;
        float actHeight = heightSize;

        if (widthMode == MeasureSpec.AT_MOST) {
            actWidth = Math.min(mMinWidth, widthSize);
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            actHeight = Math.min(mMinWidth, heightSize);
        }

        if (actWidth < mMinWidth) {
            actWidth = mMinWidth;
        }

        if (actHeight < mMinWidth) {
            actHeight = mMinWidth;
        }

        setMeasuredDimension((int) actWidth, (int) actHeight);

        float mCircleLeft = getMeasuredWidth() / 2 - (mOvalRadius + mCircleWidth);
        float mCircleTop = getMeasuredHeight() / 2 - (mOvalRadius + mCircleWidth);
        float mCircleRight = getMeasuredWidth() / 2 + (mOvalRadius + mCircleWidth);
        float mCircleBottom = getMeasuredHeight() / 2 + (mOvalRadius + mCircleWidth);
        mCircleArea = new RectF(mCircleLeft, mCircleTop, mCircleRight, mCircleBottom);

        float mOvalLeft = getMeasuredWidth() / 2 - (mOvalRadius);
        float mOvalTop = getMeasuredHeight() / 2 - (mOvalRadius);
        float mOvalRight = getMeasuredWidth() / 2 + (mOvalRadius);
        float mOvalBottom = getMeasuredHeight() / 2 + (mOvalRadius);
        mOvalArea = new RectF(mOvalLeft, mOvalTop, mOvalRight, mOvalBottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //先画圈
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setColor(mCircleColor);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(mCircleArea, 180, 90, false, mPaint);

    }
}
