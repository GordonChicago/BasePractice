package com.basepractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.basepractice.view.CircleProgressBar;

/**
 * Created by Administrator on 2016/11/25.
 */

public class CircleProgressBarActivity extends Activity implements CircleProgressBar.CircleProgressListener {
    CircleProgressBar mBar;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_progressbar_layout);
        mBar = (CircleProgressBar) findViewById(R.id.circle_progressBar);
        mBar.setProgressListener(this);
        mBar.setStartAngle(178);
        mBar.setMode(CircleProgressBar.MODE_ClockWise_BACK);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                count = 1;
                mBar.animate(300);
            }
        }, 1000);

    }

    @Override
    public void onPercent(float percent) {
        if (percent == 1) {
            count++;
            if (count % 2 == 0) {
                mBar.setMode(CircleProgressBar.MODE_CounterClockWise);
            } else {
                mBar.setMode(CircleProgressBar.MODE_ClockWise_BACK);
            }
            mBar.animate(1000);
        }
    }
}
