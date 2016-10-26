package com.basepractice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;

import com.basepractice.util.Tag;

public class MessengerTest extends Activity {
    public static final String TAG = MessengerTest.class.getSimpleName();
    private Messenger mSender;//mSender的初始化，在服务连接时调用,onServiceConnected
    private Messenger mReceiver = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            if (data != null) {
                String response = data.getString("service");
                String content = etText.getText().toString();
                content = content + response;
                if (content != null) {
                    content = content + "-";
                    etText.setText(content);
                    etText.setSelection(content.length());
                }
            }
        }
    });
    private EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messenger_test);
        etText = (EditText) findViewById(R.id.et_serviceData);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent serviceIntent = new Intent(this, MessengerService.class);
        bindService(serviceIntent, mCon, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mCon);
    }

    public void click(View view) {
        Tag.i(TAG, "click");
//        mSenderHandler.sendEmptyMessage(0);
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        long [] pattern = {100,400,100,400};
        vibrator.vibrate(500);
    }

    private Handler mSenderHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mSender != null) {
                Message msgO = Message.obtain();
                msgO.replyTo = mReceiver;
                try {
                    Bundle data = new Bundle();
                    data.putString("client", "clientData");
                    msgO.setData(data);
                    mSender.send(msgO);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    };

    private ServiceConnection mCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mSender = new Messenger(service);
            Tag.i(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Tag.i(TAG, "onServiceDisconnected");
        }
    };
}
