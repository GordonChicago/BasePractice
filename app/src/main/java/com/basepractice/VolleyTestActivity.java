package com.basepractice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/10/11.
 */

public class VolleyTestActivity extends Activity {
    RequestQueue requestQueue;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_test);
        context = this;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void get(View view){
        String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=335adcc4e891ba4e4be6d7534fd54c5d";
        StringRequest strRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context,volleyError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(strRequest);
    }

    public void post(View view){
        String url = "http://apis.juhe.cn/mobile/get?";
        StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context,volleyError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> maps = new HashMap<>();
                maps.put("phone","13429667914");
                maps.put("key","335adcc4e891ba4e4be6d7534fd54c5d");
                return maps;
            }
        };
        requestQueue.add(strRequest);
    }
}
