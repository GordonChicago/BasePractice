package com.basepractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2016/10/17.
 */

public class WaveView extends View {
    private Context mContext;
    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
