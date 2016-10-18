package com.basepractice;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

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

    public void tanslationAnimation(View view){
        View txtview = findViewById(R.id.text1);
        TranslateAnimation translateAnimation = new TranslateAnimation(100,100,0,100);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        txtview.startAnimation(translateAnimation);
    }
    public void rotateAnimation(View view){
        View txtview = findViewById(R.id.text1);
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,txtview.getWidth()/4,txtview.getHeight()/2);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        txtview.startAnimation(rotateAnimation);
    }

    public void scacleAnimation(View view){
        View txtview = findViewById(R.id.text1);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,2,1,1);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        txtview.startAnimation(scaleAnimation);
    }
}