package com.basepractice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.ViewDragHelper;

/**
 * Created by Administrator on 2016/10/8.
 */
public class ViewDragerHelpeActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdraghelper_test);

        ViewDragHelper.create(null,null);
    }
}
