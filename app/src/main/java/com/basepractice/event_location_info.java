package com.basepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.basepractice.util.Tag;

public class event_location_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_location_info);

        View view = findViewById(R.id.viewtest);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    return true;
                }
                Tag.i("event_location_info", "getX:" + event.getX() + ",getY:" + event.getY() + ",getRawX:" + event.getRawX() + ",getRawY:" + event.getRawY());
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
