package com.basepractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.basepractice.util.ViewUtils;

/**
 * Created by admin on 2016/10/22.
 */

public class MikeVolumView extends View {
    private static final int DEFAULT_WIDTH = 200;//dp
    private static final int DEFAULT_HEIGHT = 200;//dp
    private static final int DEFAULT_CUREHEIGHT = 20;//dp
    private static final float VOLUMN_MAX = 30;

    private Context mContext;

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mCurveHeight;
    private int mVolumnMaxHeight;

    private int mColor;
    private float mVolumn = 15;

    public MikeVolumView(Context context) {
        this(context,null);
    }

    public MikeVolumView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MikeVolumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mPaint = new Paint();
        mPaint.setStrokeWidth(4);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ViewUtils.measureSize(mContext,widthMeasureSpec,DEFAULT_WIDTH),
                ViewUtils.measureSize(mContext,heightMeasureSpec,DEFAULT_HEIGHT));
        mWidth =  getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCurveHeight = (int)ViewUtils.dpToPx(mContext,DEFAULT_CUREHEIGHT);
        mVolumnMaxHeight = mHeight * 3 / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //判断需不需要绘制圆弧
        float percent = this.mVolumn / VOLUMN_MAX;
        int height = (int)(percent * mVolumnMaxHeight);

        if(height < mCurveHeight){
            //需要绘制小圆弧
            float fHeight = height;
            float percentCurve = fHeight / mCurveHeight;
            int curWidth = (int)(percentCurve * mWidth);
            int left = (mWidth - curWidth) / 2;
            int top = (mHeight - 2 * height);
            RectF cureRect = new RectF(left,top,left+curWidth,top+2*height);
            canvas.drawArc(cureRect,0,180,true,mPaint);
        }else{
            //需要绘制大圆弧,并且加矩形
            //绘制大圆弧
            RectF cureRect = new RectF(0,mHeight - mCurveHeight * 2,mWidth,mHeight);
            canvas.drawArc(cureRect,0,180,true,mPaint);
            //绘制音量矩形
            Rect rectangeRect = new Rect(0,mHeight-height,mWidth,mHeight - mCurveHeight);
            canvas.drawRect(rectangeRect,mPaint);
        }
    }

    public void setVolumn(float volumn) {
        this.mVolumn = volumn;
        invalidate();
    }
}
