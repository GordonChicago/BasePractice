package com.basepractice.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.basepractice.R;
import com.basepractice.util.Tag;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/30.
 */

public class RxJavaMain extends Activity {
    private static final String TAG = RxJavaMain.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_layout);
    }

    public void schedulerThread(View view) {
        Log.i(TAG, "mainThread:" + Thread.currentThread().getName());
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.i(TAG, "call:" + Thread.currentThread().getName());
                subscriber.onNext("1");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                Log.i(TAG, "doOnSubscribe:" + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext:" + Thread.currentThread().getName());
            }

            @Override
            public void onStart() {
                super.onStart();
                Log.i(TAG, "onStart:" + Thread.currentThread().getName());
            }
        });
    }
}

