package com.basepractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.basepractice.view.CircleProgressBar;

/**
 * Created by Administrator on 2016/11/25.
 */

public class CircleProgressBarActivity extends Activity {
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mBar.animate(3000);
            mHandler.sendEmptyMessageDelayed(0,1000);
        }
    };
    CircleProgressBar mBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_progressbar_layout);
        mBar = (CircleProgressBar) findViewById(R.id.circle_progressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mHandler.sendEmptyMessageDelayed(0,1000);

    }
}
