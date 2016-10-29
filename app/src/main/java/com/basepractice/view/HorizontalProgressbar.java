package com.basepractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.basepractice.R;
import com.basepractice.util.Tag;
import com.basepractice.util.ViewUtils;

/**
 * Created by admin on 2016/10/22.
 */

public class HorizontalProgressbar extends ProgressBar {
    public static final String TAG = HorizontalProgressbar.class.getSimpleName();
    private static final int DEFAULT_REACH_HEIGHT = 2;//dp
    private static final int DEFAULT_REACH_COLOR = 0xFFFC00D1;//dp
    private static final int DEFAULT_UNREACH_HEIGHT = 2;//dp
    private static final int DEFAULT_UNREACH_COLOR = 0xFFD3D6DA;//dp
    private static final int DEFAULT_TEXT_COLOR = DEFAULT_REACH_COLOR;//dp
    private static final int DEFAULT_TEXT_SIZE = 10;//sp
    private static final int DEFAULT_TEXT_OFFSET = 10;//dp

    protected float mReachHeight = DEFAULT_REACH_HEIGHT;
    protected int mReachColor = DEFAULT_REACH_COLOR;
    protected float mUnReachHeight = DEFAULT_UNREACH_HEIGHT;
    protected int mUnReachColor = DEFAULT_UNREACH_COLOR;
    protected float mTextSize = ViewUtils.spToPx(getContext(), DEFAULT_TEXT_SIZE);
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    protected float mTextOffset = ViewUtils.dpToPx(getContext(), DEFAULT_TEXT_OFFSET);

    private Context mContext;
    protected Paint mPaint;
    protected int mRealWidth;

    public HorizontalProgressbar(Context context) {
        this(context, null);
    }

    public HorizontalProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressbar);
        mReachColor = typedArray.getColor(R.styleable.HorizontalProgressbar_progress_reach_color, mReachColor);
        mReachHeight = typedArray.getDimension(R.styleable.HorizontalProgressbar_progress_reach_height, mReachHeight);
        mTextOffset = typedArray.getDimension(R.styleable.HorizontalProgressbar_progress_text_offset, mTextOffset);
        mTextColor = typedArray.getColor(R.styleable.HorizontalProgressbar_progress_text_color, mTextColor);
        mTextSize = typedArray.getDimension(R.styleable.HorizontalProgressbar_progress_text_size, mTextSize);
        mUnReachColor = typedArray.getColor(R.styleable.HorizontalProgressbar_progress_unreach_color, mUnReachColor);
        mUnReachHeight = typedArray.getDimension(R.styleable.HorizontalProgressbar_progress_unreach_height, mUnReachHeight);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        //需要正确显示的文字
        float percent = getProgress() * 1.0f / getMax();
        String strPercent = percent * 100 + "%";
        if (percent < 1 && strPercent.length() > 3) {
            strPercent = strPercent.substring(0, 2) + "%";
        } else if(percent == 1){
            strPercent = strPercent.substring(0,3) + "%";
        }

        Tag.i(TAG,"---"+strPercent);

        //测量文字范围
        Rect txtBound = new Rect();
        mPaint.getTextBounds(strPercent, 0, strPercent.length(), txtBound);

        //计算相应的坐标
        float reachLength = (mRealWidth - txtBound.width() - mTextOffset / 2) * percent;
        boolean isReachWidth = (reachLength + mTextOffset / 2 + txtBound.width()) >= mRealWidth;
        if (isReachWidth) {
            reachLength = mRealWidth - txtBound.width() - mTextOffset / 2;
        }

        //划线
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);
        canvas.drawLine(getPaddingLeft(), getHeight() / 2, getPaddingLeft() + reachLength, getHeight() / 2, mPaint);

        //写文字
        mPaint.setColor(mTextColor);
        canvas.drawText(strPercent, getPaddingLeft() + reachLength + mTextOffset / 2, getHeight() / 2 + txtBound.height() / 2, mPaint);

        //划线
        if (!isReachWidth) {
            mPaint.setColor(mUnReachColor);
            mPaint.setStrokeWidth(mUnReachHeight);
            boolean needDrawRightLine = false;
            needDrawRightLine = (getPaddingLeft() + reachLength + mTextOffset + txtBound.width()) < mRealWidth;
            if (needDrawRightLine) {
                float rightLineLength  = mRealWidth - (getPaddingLeft() + reachLength + mTextOffset + txtBound.width());
                float rightLineStartX = getPaddingLeft() + reachLength + mTextOffset + txtBound.width();
                canvas.drawLine(rightLineStartX,getHeight()/2,rightLineStartX + rightLineLength,getHeight()/2,mPaint);
            }
        }

    }

    public int measureHeight(int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int result = 0;
        if (heightMode == MeasureSpec.EXACTLY) {
            result = heightSize;
        } else {
            result = getPaddingTop() + getPaddingBottom() + (int) Math.max(Math.max(mReachHeight, Math.abs(mPaint.descent() - mPaint.ascent())), mUnReachHeight);
            if (heightMode == MeasureSpec.AT_MOST) {
                result = Math.min(heightSize, result);
            }
        }
        return result;
    }
}
