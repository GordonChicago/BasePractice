package com.basepractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.basepractice.R;
import com.basepractice.util.ViewUtils;

/**
 * Created by admin on 2016/10/22.
 */

public class HorizontalProgressbar extends ProgressBar {
    private static final int DEFAULT_REACH_HEIGHT = 2;//dp
    private static final int DEFAULT_REACH_COLOR = 0xFFFC00D1;//dp
    private static final int DEFAULT_UNREACH_HEIGHT = 2;//dp
    private static final int DEFAULT_UNREACH_COLOR = 0xFFD3D6DA;//dp
    private static final int DEFAULT_TEXT_COLOR = DEFAULT_REACH_COLOR;//dp
    private static final int DEFAULT_TEXT_SIZE = 10;//sp
    private static final int DEFAULT_TEXT_OFFSET = 10;//dp

    private float mReachHeight = DEFAULT_REACH_HEIGHT;
    private int mReachColor = DEFAULT_REACH_COLOR;
    private float mUnReachHeight = DEFAULT_UNREACH_HEIGHT;
    private int mUnReachColor = DEFAULT_UNREACH_COLOR;
    private float mTextSize = ViewUtils.spToPx(getContext(),DEFAULT_TEXT_SIZE);
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private float mTextOffset = ViewUtils.dpToPx(getContext(),DEFAULT_TEXT_OFFSET);

    private Context mContext;
    private Paint mPaint = new Paint();
    private int mRealWidth;

    public HorizontalProgressbar(Context context) {
        this(context,null);
    }

    public HorizontalProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizontalProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressbar);
        mReachColor = typedArray.getColor(R.styleable.HorizontalProgressbar_progress_reach_color,mReachColor);
        mReachHeight = typedArray.getDimension(R.styleable.HorizontalProgressbar_progress_reach_height,mReachHeight);
        mTextOffset = typedArray.getDimension(R.styleable.HorizontalProgressbar_progress_text_offset,mTextOffset);
        mTextColor = typedArray.getColor(R.styleable.HorizontalProgressbar_progress_text_color,mTextColor);
        mTextSize = typedArray.getDimension(R.styleable.HorizontalProgressbar_progress_text_size,mTextSize);
        mUnReachColor = typedArray.getColor(R.styleable.HorizontalProgressbar_progress_unreach_color,mUnReachColor);
        mUnReachHeight = typedArray.getDimension(R.styleable.HorizontalProgressbar_progress_unreach_height,mUnReachHeight);
        typedArray.recycle();

        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),height);
        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }
    public int measureHeight(int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int result = 0;
        if(heightMode == MeasureSpec.EXACTLY){
            result = heightSize;
        }else{
            result = getPaddingTop() + getPaddingBottom() + (int)Math.max(Math.max(mReachHeight,Math.abs(mPaint.descent()-mPaint.ascent())),mUnReachHeight);
            if(heightMode == MeasureSpec.AT_MOST){
                result = Math.min(heightSize,result);
            }
        }
        return result;
    }
}
