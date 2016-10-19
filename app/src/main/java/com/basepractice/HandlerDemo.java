package com.basepractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2016/10/19.
 */

public class HandlerDemo extends Activity {
    private final String TAG = HandlerDemo.class.getSimpleName();
    private TextView mName;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            showLog("mHandlerThreadID:"+Thread.currentThread().getId());
            showLog("mHandlerThreadID:"+msg.what);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_demo);
        mName = (TextView) findViewById(R.id.tv_name);
        //当前主线程ID
        showLog("MainThreadID:"+Thread.currentThread().getId());
        myThread.start();
        Message msg = new Message();
        msg.what = 10;
        myThread.sendMessage(msg);
        mHandler.sendMessage(msg);
    }

    private void showLog(String msg){
        Log.i(TAG,msg);
    }

    public MThread myThread = new MThread();
    private class MThread extends Thread{
        Handler threadHandler;
        @Override
        public void run() {
            showLog("threadHandlerThreadID:"+Thread.currentThread().getId());
            Looper.prepare();
            threadHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    showLog("threadHandlerThreadID:"+Thread.currentThread().getId());
                    showLog("threadHandlerThreadID:"+msg.what);
                }
            };
            showLog("threadHandlerThreadID:"+(threadHandler==null));
            Looper.loop();
        }

        public void sendMessage(Message msg){
            if(threadHandler != null) {
                Toast.makeText(HandlerDemo.this,"isToast",Toast.LENGTH_SHORT).show();
                threadHandler.sendMessage(msg);
            }else{
                Toast.makeText(HandlerDemo.this,"is null",Toast.LENGTH_SHORT).show();
            }
        }
    };

}
