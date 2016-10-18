package com.basepractice.view;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.basepractice.R;

/**
 * Created by admin on 2016/10/18.
 */

public class FocusButton extends Button {
    private Context mContext;
    private View mView;
    public FocusButton(Context context) {
        this(context,null);
    }

    public FocusButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FocusButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            mView.setVisibility(VISIBLE);
            mView.setFocusable(true);
            mView.setFocusableInTouchMode(true);
            mView.requestFocus();
            Toast.makeText(mContext,"requestFocus",Toast.LENGTH_SHORT).show();
            return false;
        }
        return super.onTouchEvent(event);
    }

    public void setShowView(View view) {
        this.mView = view;
    }
}
