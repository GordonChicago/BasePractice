package com.basepractice.view;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/9/26.
 */
public class DrawMusicView extends View {
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    public DrawMusicView(Context context) {
        this(context,null);
    }

    public DrawMusicView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
