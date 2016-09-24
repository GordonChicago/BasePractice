package com.basepractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2016/9/22.
 */
public class ViewPropertyTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_property_animate);
    }
    public void propertyAnimate(View btn) {
        ViewPropertyAnimator viewPropertyAnimator = findViewById(R.id.tv_helloworld).animate().translationXBy(getPix(200));
        viewPropertyAnimator.setInterpolator(new LinearInterpolator());
        viewPropertyAnimator.setDuration(5000);
        viewPropertyAnimator.start();
    }

    private int getPix(int dp) {
        int left = (int)(getResources().getDisplayMetrics().density * dp + 0.5f);
        return left;
    }
}
