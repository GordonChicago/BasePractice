package com.basepractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.basepractice.util.ViewUtils;

/**
 * Created by Administrator on 2016/10/19.
 */
public class MikeView extends ImageView {
    public static final String TAG = MikeView.class.getSimpleName();
    public static final int STATE_RECORDING = 1;
    public static final int STATE_RECORDING_WANT_CANCEL = 2;
    private OnClickListener mClickListener;
    private MikeStateInterface mMikeInterface;
    private long mDownTime;
    private float mDownX;
    private float mDownY;
    private int mState;
    private float mRadius;

    public MikeView(Context context) {
        super(context);
    }

    public MikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MikeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                showLog("MotionEvent.ACTION_DOWN " + event.getRawX() + "," + event.getRawY());
                mDownTime = System.currentTimeMillis();
                mDownX = event.getRawX();
                mDownY = event.getRawY();
                mRadius = ViewUtils.dpToPx(getContext(), 50);
                return true;
            case MotionEvent.ACTION_MOVE:
                showLog("MotionEvent.ACTION_MOVE " + event.getRawX() + "," + event.getRawY());
                if (System.currentTimeMillis() - mDownTime > 500 && mState != STATE_RECORDING && mState != STATE_RECORDING_WANT_CANCEL) {
                    mState = STATE_RECORDING;
                    if (mMikeInterface != null) {
                        mMikeInterface.startRecord();
                    }
                }

                float nowX = event.getRawX() - mDownX;
                float nowY = event.getRawY() - mDownY;
                double distance = Math.sqrt(nowX * nowX + nowY * nowY);
                //如果正在录制,并且移动距离超出了半径,则提示松开取消发送
                if (distance > mRadius && mState == STATE_RECORDING) {
                    mState = STATE_RECORDING_WANT_CANCEL;
                    if (mMikeInterface != null) {
                        mMikeInterface.wantCancel();
                    }
                } else if(distance <= mRadius){
                    mState = STATE_RECORDING;
                    if (mMikeInterface != null) {
                        mMikeInterface.resumeRecord();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showLog("MotionEvent.ACTION_UP " + event.getRawX() + "," + event.getRawY());
                long duration = System.currentTimeMillis() - mDownTime;
                //如果触摸时间在一定时间内,则触发点及事件,并重置状态
                if (duration < 500) {
                    if (mClickListener != null) {
                        mClickListener.onClick(this);
                    }
                } else {
                    if (mState == STATE_RECORDING) {
                        if(mMikeInterface != null) {
                            mMikeInterface.send();
                        }
                    } else if (mState == STATE_RECORDING_WANT_CANCEL) {
                        if (mMikeInterface != null) {
                            mMikeInterface.cancel();
                        }
                    }
                }
                reset();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mClickListener = l;
    }

    public void showLog(String msg) {
        Log.i(TAG, msg);
    }

    public void reset(){
        mState = 0;
    }

    public void setMikeInterface(MikeStateInterface mMikeInterface) {
        this.mMikeInterface = mMikeInterface;
    }

    public interface MikeStateInterface {
        public void startRecord();

        public void wantCancel();

        public void resumeRecord();

        public void cancel();

        public void send();
    }
}
