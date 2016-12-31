package com.basepractice;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
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

        ObjectAnimatorTest();

        new LayoutAnimationController(null,0.5f);
    }

    public void ObjectAnimatorTest(){
        final View view2 = findViewById(R.id.text2);
        final View view3 = findViewById(R.id.text3);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(view2, "translationX",0,100,900).setDuration(1000).start();
                ObjectAnimator.ofFloat(view3, "translationX",0,100).setDuration(500).start();
            }
        });
    }

    public void tanslationAnimation(View view) {
        View txtview = findViewById(R.id.text1);
        //默认是在父View中的位置,margin值其中会影响位置
        TranslateAnimation translateAnimation = new TranslateAnimation(100, 100, 100, 0);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        txtview.startAnimation(translateAnimation);
    }

    public void rotateAnimation(View view) {
        View txtview = findViewById(R.id.text1);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, txtview.getWidth() / 4, txtview.getHeight() / 2);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        txtview.startAnimation(rotateAnimation);
    }

    public void scacleAnimation(View view) {
        View txtview = findViewById(R.id.text1);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 1);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        txtview.startAnimation(scaleAnimation);
    }
}