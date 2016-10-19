package com.basepractice.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.basepractice.R;
import com.basepractice.util.ViewUtils;

/**
 * Created by Administrator on 2016/10/19.
 */
public class FloatMikeView extends View {
    private static final String TAG = FloatMikeView.class.getSimpleName();
    private Context mContext;
    private Bitmap mikeCenter;
    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap mColorBitmap;
    private String mFloatColor = "#FF34B3";
    public FloatMikeView(Context context) {
        this(context,null);
    }

    public FloatMikeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FloatMikeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mikeCenter = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.mike_center).copy(Bitmap.Config.RGB_565,true);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mColorBitmap = Bitmap.createBitmap(mikeCenter.getWidth(),mikeCenter.getHeight(), Bitmap.Config.ARGB_8888) ;
        mCanvas = new Canvas(mColorBitmap);
        print("ceterWidth:"+mikeCenter.getWidth()+","+mikeCenter.getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ViewUtils.measureSize(mContext,widthMeasureSpec,(int)ViewUtils.pxTodp(mContext,mikeCenter.getWidth())),
                ViewUtils.measureSize(mContext,heightMeasureSpec,(int)ViewUtils.pxTodp(mContext,mikeCenter.getHeight())));
        print("mwidth:"+getMeasuredWidth()+","+getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mikeCenter,0,0,mPaint);

        mCanvas.drawColor(Color.parseColor(mFloatColor));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(mColorBitmap,0,0,mPaint);

    }

    public void print(String msg){
        Log.i(TAG,msg);
    }
}
