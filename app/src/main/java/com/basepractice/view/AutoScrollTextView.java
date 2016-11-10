package com.basepractice.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.basepractice.util.Tag;
import com.basepractice.util.ViewUtils;

/**
 * 数字滚动控件,不支持padding
 */
public class AutoScrollTextView extends View {
    public static final String TAG = AutoScrollTextView.class.getSimpleName();
    public static final int DEFAULT_WIDTH = 50;
    private Context mContext;

    private Paint mDownPaint;
    private Rect mDownDigitBounds;

    private Paint mUpPaint;
    private Rect mUpDigitBounds;

    private boolean mAnimateMode = false;
    private float mPercent;

    private float mMaxTextSize = 100;
    private float mMinTextSize = 50;

    private int mMinWidth;
    private int mMinHeight;

    private float mMoveDistance;

    private int mMinAlpha = 100;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            start();
            mHandler.sendEmptyMessageDelayed(0, 3 * 1000);
        }
    };

    public AutoScrollTextView(Context context) {
        this(context, null);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化Context
        mContext = context;
        //初始化画笔
        mDownPaint = new Paint();
        mDownPaint.setDither(true);
        mDownPaint.setAntiAlias(true);
        //
        mDownDigitBounds = new Rect();

        //初始化画笔
        mUpPaint = new Paint();
        mUpPaint.setDither(true);
        mUpPaint.setAntiAlias(true);
        //
        mUpDigitBounds = new Rect();

        mHandler.sendEmptyMessageDelayed(0, 3 * 1000);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureMinSize();
        int mearWidthSize = ViewUtils.measureSize(mContext, widthMeasureSpec, mMinWidth);
        int mearHeightSize = ViewUtils.measureSize(mContext, heightMeasureSpec, mMinHeight);
        setMeasuredDimension(mearWidthSize, mearHeightSize);


        int textHeight = getMeasuredHeight();
        mDownPaint.setTextSize(mMinTextSize);
        mDownPaint.getTextBounds("0", 0, 1, mDownDigitBounds);
        mMoveDistance = textHeight / 2;
        Tag.i(TAG, "mMinWidth:" + mMinWidth + ",mMinHeight:" + mMinHeight + ",textHeight:" + textHeight + ",moveDistance:" + mMoveDistance);
    }

    private void measureMinSize() {
        mDownPaint.setTextSize(mMaxTextSize);
        mDownPaint.getTextBounds("0", 0, 1, mDownDigitBounds);
        mMinWidth = mDownDigitBounds.width();
        mMinHeight = mDownDigitBounds.height();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);

        String text = "0";
        mDownPaint.setColor(Color.RED);

        if (!mAnimateMode) {
            //直接显示文本,不加动画
            mDownPaint.setTextSize(100);
            mDownPaint.getTextBounds(text, 0, text.length(), mDownDigitBounds);
            canvas.drawText(text, 0, text.length(), getMeasuredWidth() / 2 - mDownDigitBounds.width() / 2, getMeasuredHeight() / 2 + mDownDigitBounds.height() / 2, mDownPaint);
        } else {
            //实际移动的ju
            float actMove = mMoveDistance * mPercent;

            //上部移动
            float downTextSize = mMaxTextSize * (1 - mPercent);
            if (downTextSize < mMinTextSize) {
                downTextSize = mMinTextSize;
            }
            mDownPaint.setTextSize(downTextSize);
            mDownPaint.getTextBounds("0", 0, 1, mDownDigitBounds);

            float downX = getMeasuredWidth() / 2 - mDownDigitBounds.width() / 2;
            float downY = getMeasuredHeight() / 2 + actMove + mDownDigitBounds.height();
            if (downY >= getMeasuredHeight()) {
                downY = getMeasuredHeight();
            }
            int downAlpha = (int) (255 * (1f - mPercent));
            if (downAlpha < mMinAlpha) {
                downAlpha = mMinAlpha;
            }
            mDownPaint.setAlpha(downAlpha);
            canvas.drawText(text, 0, text.length(), downX, downY, mDownPaint);

            //下部移动
            float upTextSize = mMaxTextSize * mPercent;
            if (upTextSize < mMinTextSize) {
                upTextSize = mMinTextSize;
            }

            mUpPaint.setTextSize(upTextSize);
            mUpPaint.getTextBounds("0", 0, 1, mUpDigitBounds);

            float upX = getMeasuredWidth() / 2 - mUpDigitBounds.width() / 2;
            float upY = actMove + mUpDigitBounds.height();
            if (upY > (getMeasuredHeight() / 2 + mUpDigitBounds.height() / 2)) {
                upY = getMeasuredHeight() / 2 + mUpDigitBounds.height() / 2;
            }
            int upAlpha = (int) (255 * mPercent);
            if (upAlpha < mMinAlpha) {
                upAlpha = mMinAlpha;
            }
            mUpPaint.setAlpha(upAlpha);
            canvas.drawText("0", 0, 1, upX,upY, mUpPaint);
        }
    }

    public void start() {
        mAnimateMode = true;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 100f);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float anValue = (Float) animation.getAnimatedValue();
                if (anValue != null) {
                    float nowValue = anValue.floatValue();
                    mPercent = nowValue / 100f;
                    invalidate();
                }
            }
        });
        valueAnimator.start();
    }
}
