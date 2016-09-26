package com.basepractice.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.basepractice.R;
import com.basepractice.util.Tag;
import com.basepractice.util.ViewUtils;

/**
 * Created by Administrator on 2016/9/24.
 */
public class DrawTextView extends TextView {
    private int DEFAULT_WIDTH = 100; //数值为dp
    private int DEFAULT_HEIGHT = 100; //数值为dp
    private Context mContext;

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Bitmap mBitmapWoNiu;

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;


    private int mWidth;
    private int mHeight;
    private int mTranslate;

    public DrawTextView(Context context) {
        this(context,null);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);//asdf

        mContext = context;
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.black_cat).copy(Bitmap.Config.ARGB_8888, true);
        mBitmapWoNiu = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.woniu).copy(Bitmap.Config.ARGB_8888, true);
        mCanvas = new Canvas(mBitmap);

        //初始化画笔
        mPaint1 = new Paint();
        mPaint1.setColor(mContext.getResources().getColor(R.color.blue));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
        Log.i(Tag.VIEW_TEST,"11111111");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawGradientColor(canvas);
        Log.i(Tag.VIEW_TEST,"onDraw");
    }

    private void drawBlueYellowColor(Canvas canvas){
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);
        canvas.save();
        canvas.translate(10,10);
        super.onDraw(canvas);
        canvas.restore();
    }

    private void drawGradientColor(Canvas canvas){
        super.onDraw(canvas);
        if(mMatrix!=null){
            mTranslate += mWidth/5;
            if(mTranslate>2*mWidth){
                mTranslate=-mWidth;
            }
            mMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mMatrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(Tag.VIEW_TEST,"onMeasure");
        setMeasuredDimension(ViewUtils.measureSize(getContext(),widthMeasureSpec,DEFAULT_WIDTH),
                ViewUtils.measureSize(getContext(),heightMeasureSpec,DEFAULT_HEIGHT));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(Tag.VIEW_TEST,"onSizeChanged");
        if(mWidth==0){
            mWidth = getMeasuredWidth();
            if(mWidth>0){
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0,0,mWidth,0,
                        new int[]{Color.BLUE,0xffffff,Color.BLUE},null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mMatrix = new Matrix();
            }

        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i(Tag.VIEW_TEST,"onFinishInflate");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(Tag.VIEW_TEST,"onLayout");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(Tag.VIEW_TEST,"onTouchEvent");
        return super.onTouchEvent(event);
    }
}
