package com.example.customapplication;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by David on 2017/11/5.
 */

public class MainActivity3 extends Activity {

    private String TAG = "David - MainActivity3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        Log.d(TAG, "onRestart");
        super.onRestart();
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        Log.d(TAG, "onStart");
        super.onStart();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        Log.d(TAG, "onResume");
        super.onResume();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        Log.d(TAG, "onStop");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

}