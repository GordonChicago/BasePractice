package com.basepractice;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Class[] activitys = new Class[]{ViewPropertyTest.class,MeasuedViewActivity.class,MScrollViewActivity.class
    ,EventDispatchActivity.class,ListViewActivity.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView)findViewById(R.id.listview);
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return activitys.length;
            }

            @Override
            public Object getItem(int position) {
                return activitys[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(getBaseContext(),null);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(18);
                textView.setHeight(100);
                textView.setTextColor(Color.BLACK);
                textView.setText(activitys[position].getSimpleName());
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,activitys[position]);
                        startActivity(intent);
                    }
                });
                return textView;
            }
        });
    }
}
