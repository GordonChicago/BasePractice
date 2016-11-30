package com.basepractice.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.basepractice.R;
import com.basepractice.util.Tag;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/30.
 */

public class RxJavaMain extends Activity {
    private static final String TAG = RxJavaMain.class.getSimpleName();
    private ImageView ivFirstImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_layout);

        ivFirstImage = (ImageView) findViewById(R.id.iv_firstImage);

        rxJavaMethod1();
    }

    private void rxJavaMethod1() {
        //自定义观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Tag.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Tag.i(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Tag.i(TAG, "onNext:" + s);
            }

        };
        //自定义被观察者,call方法中,定义了观察者回调的逻辑
        Observable<String> oob = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
                subscriber.onNext("2");
                subscriber.onNext("3");
                //if happen error,call the method
//                subscriber.onError();
                subscriber.onCompleted();

            }
        });

        oob.subscribe(observer);
    }

    private void rxJavaMethod2() {
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                Tag.i(TAG, " call:" + s);
            }
        };

        Observable obserable = Observable.from(new String[]{"1","2","3"});
        obserable.subscribe(action1);


//        Action0 action0 = new Action0() {
//            @Override
//            public void call() {
//                Tag.i(TAG, "action0");
//            }
//        };
//        obserable.doOnSubscribe(action0);
    }
}

