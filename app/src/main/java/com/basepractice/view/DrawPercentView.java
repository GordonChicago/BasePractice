package com.basepractice.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.basepractice.R;
import com.basepractice.util.ViewUtils;

/**
 * Created by Administrator on 2016/9/24.
 */
public class DrawPercentView extends View {
    private static final String TAG = DrawPercentView.class.getSimpleName();
    private int DEFAULT_WIDTH = 100;
    private int DEFAULT_HEIGHT = 100;
    private int textSize = 18;

    private int mWidth;

    //圆形参数
    private int mCircleXY;
    private float mRadius;
    //弧形参数
    private RectF mArcRectF;

    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;

    private String showText = "showText";

    private int startAngle = 270;

    public int getSweepAngle() {
        return sweepAngle;
    }

    public void setSweepAngle(int sweepAngle) {
        this.sweepAngle = sweepAngle;
        this.invalidate();
    }

    public int getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
        this.invalidate();
    }

    private int sweepAngle = 180;

    public DrawPercentView(Context context) {
        this(context,null);
    }

    public DrawPercentView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawPercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        super.onDraw(canvas);

        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,mCirclePaint);
        Log.i(TAG,"mCircleXY:"+mCircleXY+",mRadius:"+mRadius);
        canvas.drawArc(mArcRectF,startAngle,sweepAngle,false,mArcPaint);
        canvas.drawText(showText,0,showText.length(),mCircleXY,ViewUtils.spToPx(getContext(),textSize),mTextPaint);//mCircleXY,mCircleXY + (ViewUtils.spToPx(getContext(),textSize)/4)
//        canvas.save();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ViewUtils.measureSize(getContext(),widthMeasureSpec,DEFAULT_WIDTH),
                ViewUtils.measureSize(getContext(),heightMeasureSpec,DEFAULT_HEIGHT));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        Log.i(TAG,"mWidth:"+mWidth);

        mCircleXY = mWidth / 2;
        mRadius = (float) (mWidth * 0.5 /2);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.YELLOW);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mArcRectF = new RectF(
                (float)(mWidth * 0.25),
                (float)(mWidth * 0.25),
                (float)(mWidth * 0.75),
                (float)(mWidth * 0.75));

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.BLACK);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(ViewUtils.spToPx(getContext(),textSize));

    }
}
