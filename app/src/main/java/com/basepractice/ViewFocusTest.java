package com.basepractice;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.basepractice.view.FocusButton;

/**
 * Created by admin on 2016/10/18.
 */

public class ViewFocusTest extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_focus_test);

        FocusButton btnFocus = (FocusButton) findViewById(R.id.btn_focus);
        View view = findViewById(R.id.layout_focus);
        btnFocus.setShowView(view);
    }
}
