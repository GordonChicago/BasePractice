package com.basepractice;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.basepractice.util.ViewUtils;
/**
 * Created by Administrator on 2016/10/9.
 */
public class ValueAnimator extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value_animator);
    }
    public void testTranslationX(View view){
        ObjectAnimator.ofFloat(view,"translationX",ViewUtils.dpToPx(this,300)).setDuration(2000).start();
    }
}