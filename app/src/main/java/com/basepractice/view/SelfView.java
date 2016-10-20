package com.basepractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.basepractice.R;

/**
 * Created by admin on 2016/10/19.
 */

public class SelfView extends View {
    private Context mContext;

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    private int mAlpha;

    private Rect mTextBound;
    private Rect mIconRect;
    private Paint mTextPaint;

    private Bitmap mIconBitmap;
    private int color = 0x655555;
    private String text = "SelfView";
    private float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics());

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
            switch (attrIndex){
                case R.styleable.SelfView_icon:
                    BitmapDrawable bitmapDrawable = (BitmapDrawable)typedArray.getDrawable(attrIndex);
                    mIconBitmap = bitmapDrawable.getBitmap();
                    break;
                case R.styleable.SelfView_color:
                    color = typedArray.getColor(attrIndex, Color.RED);
                    break;
                case R.styleable.SelfView_text:
                    text = typedArray.getString(attrIndex);
                    break;
                case R.styleable.SelfView_text_size:
                    textSize = typedArray.getDimension(attrIndex,textSize);
                    break;
            }
        }
        typedArray.recycle();

        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(textSize);
        mTextPaint.getTextBounds(text,0,text.length(),mTextBound);

    }
}
