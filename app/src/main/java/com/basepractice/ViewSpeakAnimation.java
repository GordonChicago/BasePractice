package com.basepractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.basepractice.util.Tag;
import com.basepractice.util.ViewUtils;
import com.basepractice.view.MikeView;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ViewSpeakAnimation extends Activity implements View.OnClickListener {
    private static final String TAG = ViewSpeakAnimation.class.getSimpleName();
    private View speakLayout;
    private View gray_view;
    private View layout;
    private MikeView mikeView;
    private float minVolumn = 0;
    private float maxVolumn = 30;
    private int maxHeight = 100;//单位dp
    private float nowVolumn = minVolumn;
    private SpeechRecognizer mIat;
    private String mEngineType = SpeechConstant.TYPE_CLOUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_speak_animation);
        speakLayout = findViewById(R.id.layout_speak);
        gray_view = findViewById(R.id.gray_view);
        MikeView mikeView = (MikeView) findViewById(R.id.bottom_view);
        mikeView = (MikeView)findViewById(R.id.bottom_view);
        layout = findViewById(R.id.layout_mikeview);

        mikeView.setOnClickListener(this);
        mikeView.setMikeInterface(mMikeStateInterface);

        mIat = SpeechRecognizer.createRecognizer(this, null);
    }

    public void setParams() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

        // 设置语言
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        // 设置语言区域
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");

        mIat.setParameter(SpeechConstant.VAD_BOS, "" + 60 * 1000);

        mIat.setParameter(SpeechConstant.VAD_EOS, "" + 60 * 1000);
    }

    private MikeView.MikeStateInterface mMikeStateInterface = new MikeView.MikeStateInterface() {
        @Override
        public void startRecord() {
            layout.animate().translationY(layout.getMeasuredHeight()).setDuration(500).start();
            Tag.i("MikeView","startRecord");
            //开始录制
            //底部退出动画
            //显示录制界面
            //启动声音识别
        }

        @Override
        public void wantCancel() {
            //更改界面
        }

        @Override
        public void resumeRecord() {
            //更改界面
        }

        @Override
        public void cancel() {
            //stop cancle声音识别
        }

        @Override
        public void send() {
            //获取结果,stop
            //并且进行底部弹出动画
        }
    };

    public void bottomView_Click(View view) {
//        view.animate().translationY(view.getMeasuredHeight())
//                .setInterpolator(new AccelerateDecelerateInterpolator())
//                .setDuration(200).start();
        speakLayout.setVisibility(View.VISIBLE);
        Toast.makeText(ViewSpeakAnimation.this, "bottomView_Click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_view:
                Toast.makeText(ViewSpeakAnimation.this, "bottomView_Click", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void updateViewHeight(View view) {
        setParams();
        mIat.startListening(new RecognizerListener() {
            private float maxValue = 30;

            @Override
            public void onVolumeChanged(int i, byte[] bytes) {
                float vi = i;
                Log.i(TAG, "onVolumeChanged " + i);

                ViewGroup.LayoutParams params = gray_view.getLayoutParams();
                float dpValue = (vi / maxValue) * maxHeight;
                params.height = (int) ViewUtils.dpToPx(ViewSpeakAnimation.this, (int) dpValue);
                if (i < 10) {
                    gray_view.setAlpha(0.05f * i);
                } else {
                    gray_view.setAlpha(1f);
                }
                gray_view.setLayoutParams(params);


            }

            @Override
            public void onBeginOfSpeech() {
                Toast.makeText(getBaseContext(), "onBeginOfSpeech", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEndOfSpeech() {
                Toast.makeText(getBaseContext(), "onEndOfSpeech", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {

            }

            @Override
            public void onError(SpeechError speechError) {
                Toast.makeText(getBaseContext(), speechError.getErrorDescription(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
//        mHandler.sendEmptyMessageDelayed(0,10);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            nowVolumn += 1;
            if (nowVolumn >= maxVolumn) {
                nowVolumn = 0;
            }

            ViewGroup.LayoutParams params = gray_view.getLayoutParams();
            float dpValue = (nowVolumn / maxVolumn) * maxHeight;
            params.height = (int) ViewUtils.dpToPx(ViewSpeakAnimation.this, (int) dpValue);

            gray_view.setLayoutParams(params);
        }
    };
}
