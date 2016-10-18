package com.basepractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2016/10/17.
 */

public class CustomCircleView extends View {
    private Context mContext;
    public CustomCircleView(Context context) {
        this(context,null);
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float paintWidth = 2;

        float width = getMeasuredWidth();
        float radius = width / 2 - paintWidth;
        float cx = width/2;
        float cy = width/2;
        //画笔的基本设置
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);//paint.setARGP(int a,int r,int g,int b),paint.setAlpha(int alpha);
        paint.setTextSize(sp2px(14));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(paintWidth);

        //画圆
        canvas.drawCircle(cx,cy,radius,paint);

        //画一个点
        paint.setStrokeWidth(10);
        canvas.drawPoint(cx,cy,paint);

        //画一条线
        paint.setColor(Color.GREEN);
        canvas.drawLine(0,0,width,width,paint);

        //画多条线
        float[] points = new float[]{
                0,paintWidth,width-paintWidth,paintWidth,
                width,width/2,cx,cy,
        };
        paint.setColor(Color.MAGENTA);
        canvas.drawLines(points,paint);

        //画一个矩形
        paint.setColor(Color.parseColor("#3388ff"));
//        canvas.drawRect(0,0,width,width,paint);

        //画一个圆角矩形
        canvas.drawRoundRect(0,0,width,width,90,30,paint);
        //画一个弧形
        paint.setColor(Color.BLACK);
        canvas.drawArc(0,0,width,width,0,90,false,paint);

        //画一个椭圆
        paint.setColor(Color.RED);
        canvas.drawOval(0,width/4,width,width*3/4,paint);

        //画一个文本
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawText("hello",30,30,paint);
    }

    public int sp2px(int spValue){
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
