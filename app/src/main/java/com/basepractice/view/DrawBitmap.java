package com.basepractice.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.basepractice.R;
import com.basepractice.util.Tag;
import com.basepractice.util.ViewUtils;

/**
 * Created by Administrator on 2016/11/14.
 */
public class DrawBitmap extends View {
    public static final String TAG = DrawBitmap.class.getSimpleName();
    private Context mContext;
    private Bitmap mBitmap;
    private int mMinWidth;
    private Rect mBitmapRect;
    private Paint mPaint;
    BitmapFactory.Options mOptions;

    public DrawBitmap(Context context) {
        this(context, null);
    }

    public DrawBitmap(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawBitmap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        mOptions = new BitmapFactory.Options();
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(mContext.getResources(), R.drawable.woniu, mOptions);
        mBitmap =((BitmapDrawable) mContext.getDrawable(R.drawable.woniu)).getBitmap();
        mMinWidth = (int) ((mOptions.outWidth + mOptions.outHeight) * 1.2f);
        Tag.i(TAG, "outWidth:" + mOptions.outWidth + ",outHeight:" + mOptions.outHeight + ",mBitmap.width:" + mBitmap.getWidth());

        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int actWidthSize = widthSize;
        int actHeightSize = heightSize;

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            actWidthSize = mMinWidth;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            actWidthSize = Math.min(mMinWidth, actWidthSize);
        }

        if (mMinWidth > actWidthSize) {
            actWidthSize = mMinWidth;
        }

        if (heightMode == MeasureSpec.UNSPECIFIED) {
            actHeightSize = mMinWidth;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            actHeightSize = Math.min(mMinWidth, actHeightSize);
        }

        if (mMinWidth > actHeightSize) {
            actHeightSize = mMinWidth;
        }

        int actWidth = Math.max(actWidthSize, actHeightSize);

        Tag.i(TAG, "widthMode:" + ViewUtils.getMeasureSpecMode(widthMeasureSpec) + ",heightMode:" + ViewUtils.getMeasureSpecMode(heightMeasureSpec));
        Tag.i(TAG, "widthSize:" + widthSize + ",heightSize:" + heightSize);
        Tag.i(TAG, "actWidth:" + actWidth);

        setMeasuredDimension(actWidth, actWidth);

        //计算绘制区域
        int drawX = (getMeasuredWidth() - mOptions.outHeight) / 2 - mOptions.outWidth / 2 + mOptions.outHeight;
        int drawY = (getMeasuredHeight() - mOptions.outWidth) / 2 - mOptions.outHeight / 2;
        mBitmapRect = new Rect(drawX, drawY, drawX + mOptions.outWidth, drawY + mOptions.outHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);
        canvas.drawBitmap(mBitmap,null,mBitmapRect,null);
    }
}
