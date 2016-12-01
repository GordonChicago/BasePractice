package com.basepractice;

import android.app.Activity;
import android.os.Bundle;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2016/11/21.
 */

public class RippleAnimActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ripple_animate_layout);
    }
}
