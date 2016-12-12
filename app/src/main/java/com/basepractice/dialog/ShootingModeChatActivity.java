/**
 * @版权 : 深圳云帆世纪科技有限公司
 * @作者 : 刘群山
 * @日期 : 2015年9月19日
 */
package com.basepractice.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basepractice.R;

public class ShootingModeChatActivity extends Activity implements
        View.OnClickListener {

    private String mTaskId;
    private Context mContext;
    private Handler mhandler = new Handler();
    ImageView iv_live, iv_record, iv_close;
    TextView tv_live, tv_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_shooting_chat_mode);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mContext = this;
        initView();
    }

    private void initView() {
        iv_live = (ImageView) findViewById(R.id.iv_live);
        iv_live.setOnClickListener(this);
        iv_record = (ImageView) findViewById(R.id.iv_record);
        iv_record.setOnClickListener(this);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);

        tv_live = (TextView) findViewById(R.id.tv_live);
        tv_record = (TextView) findViewById(R.id.tv_record);

//        iv_live.setVisibility(View.INVISIBLE);
//        iv_record.setVisibility(View.INVISIBLE);
//        tv_live.setVisibility(View.INVISIBLE);
//        tv_record.setVisibility(View.INVISIBLE);

        mhandler.postDelayed(new UPAniRun(), 10);

    }

    boolean clickedClose = false;

    @Override
    public void onClick(View v) {
        clickedClose = true;
        mhandler.postDelayed(new DownAniRun(), 10);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
            return false;
        return super.dispatchKeyEvent(event);
    }

    private class UPAniRun implements Runnable {
        @Override
        public void run() {
            showUpAnimation();
        }
    }

    private class DownAniRun implements Runnable {
        @Override
        public void run() {
            showDownAnimation();
        }
    }

    public void showDownAnimation() {
//
//        iv_close.setVisibility(View.GONE);
//
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int screenHeight = metrics.heightPixels;
//
//        int[] iv_live_location = new int[2];
//        iv_live.getLocationInWindow(iv_live_location);
//
//        int[] iv_vod_location = new int[2];
//        iv_record.getLocationInWindow(iv_vod_location);
//
//        int[] iv_chat_location = new int[2];
//        iv_chat.getLocationInWindow(iv_chat_location);
//
//        TranslateAnimation liveAnimation = new TranslateAnimation(Animation.ABSOLUTE, iv_live_location[0], Animation.ABSOLUTE, iv_vod_location[0],
//                Animation.ABSOLUTE, iv_live_location[1], Animation.ABSOLUTE, screenHeight - iv_live.getHeight());
//
//        TranslateAnimation vodAnimation = new TranslateAnimation(Animation.ABSOLUTE, iv_vod_location[0], Animation.ABSOLUTE, iv_vod_location[0],
//                Animation.ABSOLUTE, iv_vod_location[1], Animation.ABSOLUTE, screenHeight - iv_record.getHeight());
//
//        TranslateAnimation chatAnimation = new TranslateAnimation(Animation.ABSOLUTE, iv_chat_location[0], Animation.ABSOLUTE, iv_vod_location[0],
//                Animation.ABSOLUTE, iv_chat_location[1], Animation.ABSOLUTE, screenHeight - iv_chat.getHeight());
//
//
//        Animation[] atrs = new Animation[]{liveAnimation, vodAnimation, chatAnimation};
//        for (Animation a : atrs) {
//            a.setDuration(300);
//        }
//
//        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
//        alphaAnimation.setDuration(250);
//        AnimationSet liveSet = new AnimationSet(true);
//        AnimationSet vodSet = new AnimationSet(true);
//        AnimationSet chatSet = new AnimationSet(true);
//        liveSet.addAnimation(alphaAnimation);
//        liveSet.addAnimation(liveAnimation);
//        vodSet.addAnimation(alphaAnimation);
//        vodSet.addAnimation(vodAnimation);
//        chatSet.addAnimation(alphaAnimation);
//        chatSet.addAnimation(chatAnimation);
//        cp_iv_live.setTag(liveSet);
//        cp_iv_record.setTag(vodSet);
//        cp_iv_chat.setTag(chatSet);
//
//        iv_live.setVisibility(View.INVISIBLE);
//        iv_record.setVisibility(View.INVISIBLE);
//        iv_chat.setVisibility(View.INVISIBLE);
//
//        tv_live.setVisibility(View.INVISIBLE);
//        tv_record.setVisibility(View.INVISIBLE);
//        tv_chat.setVisibility(View.INVISIBLE);
//
//        YFAnticipateOvershootInterpolator vodAcep = new YFAnticipateOvershootInterpolator(cp_iv_record, null, null);
//        YFAnticipateOvershootInterpolator chatAcep = new YFAnticipateOvershootInterpolator(cp_iv_chat, null, vodAcep);
//        YFAnticipateOvershootInterpolator liveAcep = new YFAnticipateOvershootInterpolator(cp_iv_live, null, chatAcep);
//        liveAcep.startAnimation();
//
//        Animation scaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        scaleAnimation.setDuration(300);
//        iv_close.startAnimation(scaleAnimation);
    }

    public void showUpAnimation() {
        Animation scaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(400);
        iv_close.startAnimation(scaleAnimation);
//        YFAnticipateOvershootInterpolator closeT = new YFAnticipateOvershootInterpolator(iv_close, null, null);
        showLog("-------execute showUpAnimation()");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;

        int[] iv_live_location = new int[2];
        iv_live.getLocationInWindow(iv_live_location);

        int[] iv_vod_location = new int[2];
        iv_record.getLocationInWindow(iv_vod_location);

        int[] iv_close_location = new int[2];
        iv_close.getLocationInWindow(iv_close_location);




        if (true) return;

        showLog("iv_live_location:" + iv_live_location[0] + "|" + iv_live_location[1] + ",iv_close_location:" + iv_close_location[0] + "," + iv_live_location[1]);

        TranslateAnimation liveAnimation = new TranslateAnimation(Animation.ABSOLUTE, iv_vod_location[0] + (iv_record.getWidth() / 4), Animation.ABSOLUTE, iv_live_location[0],
                Animation.ABSOLUTE, screenHeight - iv_live.getHeight(), Animation.ABSOLUTE, iv_live_location[1]);

        TranslateAnimation vodAnimation = new TranslateAnimation(Animation.ABSOLUTE, iv_vod_location[0] + (iv_record.getWidth() / 4), Animation.ABSOLUTE, iv_vod_location[0],
                Animation.ABSOLUTE, screenHeight - iv_record.getHeight(), Animation.ABSOLUTE, iv_vod_location[1]);

        Animation[] atrs = new Animation[]{liveAnimation, vodAnimation};
        for (Animation a : atrs) {
            a.setDuration(2000);
        }

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2000);

        Animation scaleAnimation2 = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation2.setDuration(2000);

        AnimationSet liveSet = new AnimationSet(true);
        AnimationSet vodSet = new AnimationSet(true);

        liveSet.addAnimation(scaleAnimation2);
        liveSet.addAnimation(alphaAnimation);
        liveSet.addAnimation(liveAnimation);

        vodSet.addAnimation(scaleAnimation2);
        vodSet.addAnimation(alphaAnimation);
        vodSet.addAnimation(vodAnimation);

        iv_record.setTag(vodSet);
        iv_live.setTag(liveSet);

        YFAnticipateOvershootInterpolator vodAcep = new YFAnticipateOvershootInterpolator(iv_record, new View[]{iv_record, tv_record}, null);
        YFAnticipateOvershootInterpolator liveAcep = new YFAnticipateOvershootInterpolator(iv_live, new View[]{iv_live, tv_live}, null);
        liveAcep.startAnimation();
    }

    public class YFAnticipateOvershootInterpolator implements Interpolator {
        private final float mTension;
        private View startView;
        private View[] endShowView;
        boolean startNext = false;
        private YFAnticipateOvershootInterpolator nextAcep;

        public YFAnticipateOvershootInterpolator(View startView, View[] endShowView, YFAnticipateOvershootInterpolator nextAcep) {
            mTension = 2f;//2.0f * 1.5f;
            this.startView = startView;
            this.endShowView = endShowView;
            this.nextAcep = nextAcep;
        }

        public void startAnimation() {
            showLog("-------execute startAnimation()");
            Animation animation = (Animation) startView.getTag();
            animation.setInterpolator(this);
            startView.setVisibility(View.VISIBLE);
            startView.startAnimation(animation);
        }

        private float a(float t, float s) {
            return t * t * ((s + 1) * t - s);
        }

        private float o(float t, float s) {
            return t * t * ((s + 1) * t + s);
        }

        public float getInterpolation(float t) {

            if (t == 0) {
                showLog("-------execute startAnimation()  t==0");
            } else if (t > 0.08f && !startNext) {
                showLog("-------execute startNext()  t==" + t);
                startNext = true;
                if (nextAcep != null)
                    nextAcep.startAnimation();
            } else if (t == 1) {
                showLog("-------execute startNext()  t==1");
                startView.setVisibility(View.GONE);
                if (endShowView != null && endShowView.length > 0) {
                    showLog("-------execute startNext()  t==1 endShowView " + endShowView.length);
                    for (View v : endShowView)
                        v.setVisibility(View.VISIBLE);
                }
//                if (startView.getId() == R.id.cp_iv_chat && clickedClose) {
//                    showLog("-------execute startNext()  t==1 clickedClose ");
//                    mhandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getBaseContext(),"end",Toast.LENGTH_SHORT).show();
//                            ShootingModeChatActivity smcActivity = (ShootingModeChatActivity) mContext;
//                            smcActivity.finish();
//                        }
//                    });
//                }
            }

            float result;
            if (t < 0.5f) {
                result = 0.5f * a(t * 2f, mTension);
                ;
                return result;
            } else {
                result = 0.5f * (o(t * 2f - 2.0f, mTension) + 2.0f);
                return result;
            }
        }
    }

    public void showLog(String msg) {
        android.util.Log.i("shootingChatDialogX", "shootingChatDialogX:" + msg);
    }

}
