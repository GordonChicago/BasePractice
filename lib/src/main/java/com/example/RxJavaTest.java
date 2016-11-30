package com.example;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/30.
 */

public class RxJavaTest {
    public static final String TAG = RxJavaTest.class.getSimpleName();
    public static void main(String[] args) {
        rxJavaMethod2();
    }

    /**
     * 自定义Observer\Observable
     * 回调逻辑由call中的代码定义
     */
    private static void rxJavaMethod1() {
        //自定义观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext:" + s);
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
    /**
     * 自定义Subscriber\Observable
     * 回调逻辑由call中的代码定义
     *
     * Subscriber 继承于Observer
     *  区别是多了一个onStart方法
     *  在subscribe订阅方法的时候,会调用onStart方法
     */
    private static void rxJavaMethod2(){
        //自定义观察者
        Subscriber<String> observer = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext:" + s);
            }

            @Override
            public void onStart() {
                super.onStart();
                Log.i(TAG, "onStart:");
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

        if (!observer.isUnsubscribed()) {
            observer.unsubscribe();
        }
    }

    private static void rxJavaMethod3(){

    }
}
