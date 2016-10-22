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
import android.os.Bundle;
import android.os.Parcelable;
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
    public static final int DEFAULT_WIDTH = 100;
    public static final String INSTANCE_STATUS = "instance_status";
    public static final String ALPHA_STATUS = "alpha_status";
    private Context mContext;

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    private float mAlpha = 1.0f;

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
                    mColor = typedArray.getColor(attrIndex, mColor);
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
        Tag.i("text:" + text + ",textSize:" + textSize + ",mColor:" + mColor + ",colorBlack:" + Color.BLACK);
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
        setMeasuredDimension(ViewUtils.measureSize(mContext, widthMeasureSpec, DEFAULT_WIDTH), ViewUtils.measureSize(mContext, heightMeasureSpec, DEFAULT_WIDTH));

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
        int left = (getMeasuredWidth() -getPaddingRight() -getPaddingLeft()) / 2 + getPaddingLeft() - iconActWidth / 2;
        int top = (getMeasuredHeight() -getPaddingBottom() -getPaddingTop()) / 2 + getPaddingTop() - (iconActWidth + mTextBound.height()) / 2;
        mIconRect = new Rect(left, top, left + iconActWidth, top + iconActWidth);

        Tag.i("view\r\nWidth:"+getMeasuredWidth()+"\r\nHeight:"+getMeasuredHeight()+"\r\npaddingTop:"+getPaddingTop()+"\r\npaddingBottom:"+getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //view的onDraw方法是个空实现,所以super.onDraw可写可不写
        //此处作为一种代码规范,写在此处
        super.onDraw(canvas);

        //绘制原图片
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
//        //获取图片和颜色的交集,然后取颜色的部分
        drawColorInBitmap(mAlpha);
//        //将颜色绘制的mIconBitmap上方,绘制图片完成
        canvas.drawBitmap(mBitmap, 0, 0, null);
//        //绘制原文字
        drawSourceText(canvas,mAlpha);
        drawTargteText(canvas,mAlpha);

    }
    private void drawTargteText(Canvas canvas, float alpha) {
        if (canvas == null) {
            return;
        }
        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha((int)(alpha*255));
        int textX = (getMeasuredWidth()-getPaddingLeft()-getPaddingRight()) / 2 + getPaddingLeft() - mTextBound.width() /2;
        int textY = mIconRect.bottom + mTextBound.height();//需要测试理解text的绘制y坐标为什么是text的底部
        canvas.drawText(text,textX,textY,mTextPaint);
    }


    private void drawSourceText(Canvas canvas, float alpha) {
        if (canvas == null) {
            return;
        }
        mTextPaint.setAlpha((int)((1-alpha)*255));
        mTextPaint.setColor(0xff333333);
        int textX = (getMeasuredWidth()-getPaddingLeft()-getPaddingRight()) / 2 + getPaddingLeft() - mTextBound.width() /2;
        int textY = mIconRect.bottom + mTextBound.height();//需要测试理解text的绘制y坐标为什么是text的底部
        canvas.drawText(text,textX,textY,mTextPaint);
    }

    public int getmColor() {
        return mColor;
    }

    /**
     * 在内存中绘制一张bitmap
     * 要充分理解Xfermode的使用
     * 即将要绘制的图层为Src
     * 已经存在的最上层的图层为Dst
     *
     * @param alpha
     */
    public void drawColorInBitmap(float alpha) {
        //注意创建bitmap的参数,也就是将此View的整个高宽尺寸作为一个bitmap创建的高宽
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_4444);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setAlpha((int) (alpha * 255));
        mPaint.setDither(true);//图像抖动开关,可以在底色配置时,依然保持很好的图像色彩质量
        //因为mBitmap是以View的宽高作为尺寸
        mCanvas.drawRect(mIconRect, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
    }

    public void setAlpha(float alpha){
        Tag.i(Tag.SELF_VIEW,"setAlpha:"+alpha);
        mAlpha = alpha;
        invalidate();
    }

    /**
     * 当view销毁的时候调用该方法保存参数,保存参数的目的是为了还原重现之前的状态
     * @return
     */
    @Override
    protected Parcelable onSaveInstanceState() {
        Tag.i(Tag.SELF_VIEW,"onSaveInstanceState--");
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS,super.onSaveInstanceState());
        bundle.putFloat(ALPHA_STATUS,mAlpha);
        return bundle;
    }

    /**
     * * 当view被创建的时候调用
     * @param state
     */
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle){
            Tag.i(Tag.SELF_VIEW,"onRestoreInstanceState--");
            Bundle bundle = (Bundle) state;
            Parcelable parcelable = bundle.getParcelable(INSTANCE_STATUS);
            super.onRestoreInstanceState(parcelable);
            mAlpha = bundle.getFloat(ALPHA_STATUS);
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
