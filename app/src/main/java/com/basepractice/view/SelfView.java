package com.basepractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.basepractice.R;
import com.basepractice.util.Tag;
import com.basepractice.util.ViewUtils;

/**
 * Created by admin on 2016/10/19.
 */

public class SelfView extends View {
    public static final int DEFAULT_WIDTH = 50;
    private Context mContext;

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    private int mAlpha;

    private Bitmap mIconBitmap;
    private int mColor = Color.BLACK;
    private String text = "SelfView";
    private float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

    private Rect mTextBound;
    private Rect mIconRect;
    private Paint mTextPaint;

    public SelfView(Context context) {
        this(context, null);
    }

    public SelfView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SelfView);
        int size = typedArray.getIndexCount();
        for (int i = 0; i < size; i++) {
            int attrIndex = typedArray.getIndex(i);
            switch (attrIndex) {
                case R.styleable.SelfView_icon:
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) typedArray.getDrawable(attrIndex);
                    mIconBitmap = bitmapDrawable.getBitmap();
                    break;
                case R.styleable.SelfView_color:
                    mColor = typedArray.getColor(attrIndex,mColor);
                    break;
                case R.styleable.SelfView_text:
                    text = typedArray.getString(attrIndex);
                    break;
                case R.styleable.SelfView_text_size:
                    textSize = typedArray.getDimension(attrIndex, textSize);
                    break;
            }
        }
        typedArray.recycle();
        Tag.i("text:" + text + ",textSize:" + textSize + ",mColor:" + mColor+",colorBlack:"+Color.BLACK);
        init();
    }

    /**
     * 初始化成员变量
     */
    private void init() {
        //获取文字的Rect范围
        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(mColor);
        mTextPaint.getTextBounds(text, 0, text.length(), mTextBound);
        Tag.i("left:" + mTextBound.left + ",top:" + mTextBound.top + ",right:" + mTextBound.right + ",bottom:" + mTextBound.bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*
         此时有个问题，当高宽设置为wrap_content的时候,这是的测量高宽为最大空间值，所有有必要在此场景下设置一个默认值
         */
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(ViewUtils.measureSize(mContext,widthMeasureSpec,DEFAULT_WIDTH),ViewUtils.measureSize(mContext,heightMeasureSpec,DEFAULT_WIDTH));

        /*
        确定Icon的绘制范围，此时将icon绘制为正方形
        第一步：先将icon的绘制宽度测量出来
        第二步：将icon的绘制范围测量出来
         */
        //确定icon的实际宽度
        int iconWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int iconHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextBound.height();
        int iconActWidth = Math.min(iconWidth, iconHeight);
        //确定icon的绘制范围,确定left和top，然后加上width即可
        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = getMeasuredHeight() / 2 - (iconWidth + mTextBound.height()) / 2;
        mIconRect = new Rect(left, top, left + iconActWidth, top + iconActWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //view的onDraw方法是个空实现,所以super.onDraw可写可不写
        //此处作为一种代码规范,写在此处
        super.onDraw(canvas);
        canvas.drawBitmap(mIconBitmap,null,mIconRect,null);
        drawColorTextBitmap(0.8f);
        canvas.drawBitmap(mBitmap,0,0,null);
    }

    public int getmColor() {
        return mColor;
    }

    /**
     * 在内存中绘制一张bitmap
     * 要充分理解Xfermode的使用
     * 即将要绘制的图层为Src
     * 已经存在的最上层的图层为Dst
     * @param alpha
     */
     public void drawColorTextBitmap(float alpha){
        //注意创建bitmap的参数,也就是将此View的整个高宽尺寸作为一个bitmap创建的高宽
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_4444);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setAlpha((int)(alpha * 255));
        mPaint.setDither(true);//图像抖动开关,可以在底色配置时,依然保持很好的图像色彩质量
        //因为mBitmap是以View的宽高作为尺寸
        mCanvas.drawRect(mIconRect,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap,null,mIconRect,mPaint);
    }
}
