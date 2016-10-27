package com.basepractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.basepractice.R;
import com.basepractice.util.Tag;
import com.basepractice.util.ViewUtils;

/**
 * Created by Administrator on 2016/10/26.
 */
public class LeanTextView extends View {
    public static final String TAG = LeanTextView.class.getSimpleName();
    public static final int DEFAULT_COLOR = Color.GREEN;
    public static final float DEFAULT_RADIUS = 10;
    public static final float DEFAULT_TEXT_SIZE = 10;
    public static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    public static final String DEFAULT_TEXT = LeanTextView.class.getSimpleName();
    public static final int DEFAULT_TEXT_ROTATE_DEGRESS = 45;
    public static final int LOCATION_LEFT_TOP = 1;
    public static final int LOCATION_RIGHT_TOP = 2;
    public static final int LOCATION_LEFT_BOTTOM = 3;
    public static final int LOCATION_RIGHT_BOTTOM = 4;

    private Context mContext;

    //View绘制变量
    private int mColor = DEFAULT_COLOR;//颜色
    private float mRadius = DEFAULT_RADIUS;//弧形半径,像素
    private Paint mPaint;
    private Path mPath;//路径
    private int mLocation = LOCATION_RIGHT_TOP;

    //文字需要的变量
    private float mTextSize = DEFAULT_TEXT_SIZE;
    private String mText = DEFAULT_TEXT;
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private float mTextRotateDegress = DEFAULT_TEXT_ROTATE_DEGRESS;

    private Rect mTextBound;
    private int mTextCenterX;
    private int mTextCenterY;

    //View显示text，所需要的最小变成,根据text来反向推算
    private int mMinWidth;

    public LeanTextView(Context context) {
        this(context, null);
    }

    public LeanTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeanTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.LeanTextView);
        mText = typedArray.getString(R.styleable.LeanTextView_text);
        mTextSize = typedArray.getDimension(R.styleable.LeanTextView_text_size, mTextSize);
        mTextColor = typedArray.getColor(R.styleable.LeanTextView_textColor, mTextColor);
        mTextRotateDegress = typedArray.getFloat(R.styleable.LeanTextView_textRotateDegress, mTextRotateDegress);
//        mTextMarginTop = typedArray.getDimension(R.styleable.LeanTextView_textMarginTop, mTextMarginTop);
//        mTextMarginRight = typedArray.getDimension(R.styleable.LeanTextView_textMarginRight, mTextMarginRight);
        mColor = typedArray.getColor(R.styleable.LeanTextView_color, mColor);
        mRadius = typedArray.getDimension(R.styleable.LeanTextView_cornerRadius, mRadius);
        mLocation = typedArray.getInt(R.styleable.LeanTextView_location, mLocation);
        typedArray.recycle();

        Tag.i(TAG, "denisity:" + mContext.getResources().getDisplayMetrics().density + ",mText:" + mText + ",mTextSize:" + mTextSize + ",mTextColor:" + mTextColor + ",mTextRotateDegress:" + mTextRotateDegress
                + ",mColor:" + mColor + ",mRadius:" + mRadius);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);
        mPaint.setDither(true);
        mPaint.setTextSize(mTextSize);

        mPath = new Path();
        //测量文字
        mTextBound = new Rect();
        if (mText != null && mText.length() > 0) {
            mPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
            //获取Text显示的最小边长
            float oneLength = (float) mTextBound.width() / (float) Math.sqrt(2.0f);
            float twoLength = (float) Math.sqrt(2.0f) * mTextBound.height();
            mMinWidth = (int) (oneLength + twoLength);
            Tag.i(TAG, "on constructor-mMinWidth:" + mMinWidth);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        Tag.i(TAG, "onMeasure-widthMode:" + ViewUtils.getMeasureSpecMode(widthMeasureSpec)
                +",heightMode:"+ViewUtils.getMeasureSpecMode(heightMeasureSpec)
                +",widthSize:"+widthSize+",heightSize:"+heightSize);

        int sourceMaxSize = Math.max(widthSize, heightSize);
        sourceMaxSize = Math.max(mMinWidth, sourceMaxSize);
        Tag.i(TAG, "onMeasure-sourceMaxSize:" + sourceMaxSize);
        setMeasuredDimension(sourceMaxSize, sourceMaxSize);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Tag.i(TAG, "onMeasure-width:" + width + ",height:" + height);

        //计算text中心点的位置
        int oneLeanHeight = mTextBound.width() / 2;
        int totalLeanHeight = (int) (width / Math.sqrt(2.0f));
        int perSpaceLeanHeight = (totalLeanHeight - oneLeanHeight - mTextBound.height()) / 2;
        mTextCenterY = (int) ((oneLeanHeight + perSpaceLeanHeight + mTextBound.height() / 2) / Math.sqrt(2));
        mTextCenterX = width - mTextCenterY;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Tag.i(TAG, "onSizeChanged");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Tag.i(TAG, "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Tag.i(TAG, "onDraw");

        mPath = new Path();
        RectF arcRect = new RectF();
        float startAngle = 0;
        float sweepAngle = 0;
        if (mLocation == LOCATION_LEFT_TOP) {
            mPath.moveTo(getMeasuredWidth(), 0);
            mPath.lineTo(mRadius, 0);
            mPath.lineTo(mRadius, mRadius);
            mPath.lineTo(0, mRadius);
            mPath.lineTo(0, getMeasuredHeight());
            mPath.close();
            arcRect.left = 0;
            arcRect.top = 0;
            arcRect.right = 2 * mRadius;
            arcRect.bottom = 2 * mRadius;
            startAngle = 180;
            sweepAngle = 90;
        } else if (mLocation == LOCATION_RIGHT_TOP) {
            mPath.moveTo(0, 0);
            mPath.lineTo(getMeasuredWidth() - mRadius, 0);
            mPath.lineTo(getMeasuredWidth() - mRadius, mRadius);
            mPath.lineTo(getMeasuredWidth(), mRadius);
            mPath.lineTo(getMeasuredWidth(), getMeasuredHeight());
            mPath.close();
            arcRect.left = getMeasuredWidth() - 2 * mRadius;
            arcRect.top = 0;
            arcRect.right = getMeasuredWidth();
            arcRect.bottom = 2 * mRadius;
            startAngle = 270;
            sweepAngle = 90;
        } else if (mLocation == LOCATION_LEFT_BOTTOM) {
            mPath.moveTo(0, 0);
            mPath.lineTo(0, getMeasuredHeight() - mRadius);
            mPath.lineTo(mRadius, getMeasuredHeight() - mRadius);
            mPath.lineTo(mRadius, getMeasuredHeight());
            mPath.lineTo(getMeasuredWidth(), getMeasuredHeight());
            mPath.close();
            arcRect.left = 0;
            arcRect.top = getMeasuredHeight() - 2 * mRadius;
            arcRect.right = 2 * mRadius;
            arcRect.bottom = getMeasuredHeight();
            startAngle = 90;
            sweepAngle = 90;
        } else if (mLocation == LOCATION_RIGHT_BOTTOM) {
            mPath.moveTo(getMeasuredWidth(), 0);
            mPath.lineTo(getMeasuredWidth(), getMeasuredHeight() - mRadius);
            mPath.lineTo(getMeasuredWidth() - mRadius, getMeasuredHeight() - mRadius);
            mPath.lineTo(getMeasuredWidth() - mRadius, getMeasuredHeight());
            mPath.lineTo(0, getMeasuredHeight());
            mPath.close();
            arcRect.left = getMeasuredWidth() - 2 * mRadius;
            arcRect.top = getMeasuredHeight() - 2 * mRadius;
            arcRect.right = getMeasuredWidth();
            arcRect.bottom = getMeasuredHeight();
            startAngle = 0;
            sweepAngle = 90;
        }

        //画多边形区域
        mPaint.setColor(mColor);
        canvas.drawPath(mPath, mPaint);

        //画圆弧
        canvas.drawArc(arcRect, startAngle, sweepAngle, true, mPaint);

        //画文字
        if (mText != null && mText.length() > 0) {
            mPaint.setColor(mTextColor);
            canvas.rotate(mTextRotateDegress, mTextCenterX, mTextCenterY);
            canvas.drawText(mText, mTextCenterX - mTextBound.width() / 2, mTextCenterY + mTextBound.height() / 2, mPaint);
        }
    }
}
