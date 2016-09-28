package com.basepractice.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.basepractice.R;
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

    public DrawTextView(Context context) {
        this(context,null);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);//asdf

        mContext = context;
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.black_cat);
        mBitmapWoNiu = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.woniu);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ViewUtils.measureSize(getContext(),widthMeasureSpec,DEFAULT_WIDTH),
                ViewUtils.measureSize(getContext(),heightMeasureSpec,DEFAULT_HEIGHT));
    }
}
