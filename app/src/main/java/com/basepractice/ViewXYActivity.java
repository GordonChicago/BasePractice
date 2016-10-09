package com.basepractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.basepractice.util.Tag;
/**
 * 1.MotionEvent:
 *  getX()，getY()--获取触摸点相对于自身的XY的值,坐标点在view的起始位置（左上角）
 *  getRawX(),getRawY()--获取触摸点在屏幕当中的XY的值，坐标点在屏幕的起始位置（左上角）
 * 2.通过下面方法，获取触摸点在父窗体中的坐标点：
 *  X的值是：getLeft()+getX();
 *  Y的值是：getTop()+getY();
 */
public class ViewXYActivity extends Activity {
    private static final String TAG = ViewXYActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_xy_test);
        Button btn = (Button) findViewById(R.id.btn_btn);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Tag.i(TAG,event.getX()+","+event.getY());
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }
}
