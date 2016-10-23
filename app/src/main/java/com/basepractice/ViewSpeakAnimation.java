package com.basepractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.basepractice.util.Tag;
import com.basepractice.util.ViewUtils;
import com.basepractice.view.MikeView;
/**
 * Created by Administrator on 2016/10/19.
 */
public class ViewSpeakAnimation extends Activity implements View.OnClickListener{
    private View speakLayout;
    private View gray_view;
    private View layout;
    private MikeView mikeView;
    private float minVolumn = 0;
    private float maxVolumn = 30;
    private int maxHeight = 100;//单位dp
    private float nowVolumn = minVolumn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_speak_animation);

        speakLayout = findViewById(R.id.layout_speak);
        gray_view = findViewById(R.id.gray_view);
        mikeView = (MikeView)findViewById(R.id.bottom_view);
        layout = findViewById(R.id.layout_mikeview);

        mikeView.setOnClickListener(this);
        mikeView.setMikeInterface(mMikeStateInterface);
    }

    private MikeView.MikeStateInterface mMikeStateInterface = new MikeView.MikeStateInterface() {
        @Override
        public void startRecord() {
            layout.animate().translationY(layout.getMeasuredHeight()).setDuration(500).start();
            Tag.i("MikeView","startRecord");
            //开始录制
            //底部退出动画
            //显示录制界面
            //启动声音识别
        }

        @Override
        public void wantCancel() {
            //更改界面
        }

        @Override
        public void resumeRecord() {
            //更改界面
        }

        @Override
        public void cancel() {
            //stop cancle声音识别
        }

        @Override
        public void send() {
            //获取结果,stop
            //并且进行底部弹出动画
        }
    };

    public void bottomView_Click(View view) {
//        view.animate().translationY(view.getMeasuredHeight())
//                .setInterpolator(new AccelerateDecelerateInterpolator())
//                .setDuration(200).start();
        speakLayout.setVisibility(View.VISIBLE);
        Toast.makeText(ViewSpeakAnimation.this, "bottomView_Click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_view:
                Toast.makeText(ViewSpeakAnimation.this, "bottomView_Click", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void updateViewHeight(View view){
        nowVolumn += 10;
        if(nowVolumn >= maxVolumn){
            nowVolumn = maxVolumn;
        }else if(nowVolumn<=minVolumn){
            nowVolumn = minVolumn;
        }

        ViewGroup.LayoutParams params = gray_view.getLayoutParams();
        float dpValue = (nowVolumn/maxVolumn)*maxHeight;
        params.height = (int) ViewUtils.dpToPx(this,(int)dpValue);

        gray_view.setLayoutParams(params);
    }
}
