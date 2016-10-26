package com.basepractice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class MessengerService extends Service {
    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message response = Message.obtain();
            Bundle data = new Bundle();
            data.putString("service", "serviceData");
            response.setData(data);
            try {
                msg.replyTo.send(response);//回调客户端
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    });

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }


}
