package com.basepractice;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

/**
 * Created by admin on 2016/10/20.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        SpeechUtility.createUtility(this,"appid=57ff5166");
        super.onCreate();
    }
}
