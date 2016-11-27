package com.basepractice;

import android.animation.*;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by admin on 2016/10/30.
 */

public class FrameLayoutTest extends Activity {
    private FrameLayout frameLayout;
    private Button btn_test;
    private int mContainerHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout_test);

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        btn_test = (Button) findViewById(R.id.btn_test);


        mContainerHeight = (int)this.getResources().getDisplayMetrics().density * 200;
        btn_test.setTranslationY(mContainerHeight);
    }

    public void translation(View view) {
        btn_test.setTranslationY(mContainerHeight);

        android.animation.ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mContainerHeight);
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (Integer) animation.getAnimatedValue();

                ViewGroup.LayoutParams params = frameLayout.getLayoutParams();
                params.height = height;
                frameLayout.setLayoutParams(params);

                if (height == mContainerHeight) {
                    ObjectAnimator ofFloatY = ObjectAnimator.ofFloat(btn_test, "translationY", mContainerHeight, 0);
                    ofFloatY.setDuration(1000);
                    ofFloatY.setInterpolator(new OvershootInterpolator());
                    ofFloatY.start();
                }
            }
        });
        valueAnimator.start();

    }
}
