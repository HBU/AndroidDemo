package com.example.administrator.activitylifedemo;

/**
 * Created by David on 2017/11/27.
 * Edited by David on 2020/09/28.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Activity2 extends Activity {

    /** Called when the activity is first created. */
    private static final String TAG = "DavidActivity2";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        Log.e(TAG,"onCreate");
        Button button = (Button) findViewById( R.id.button);
        button.setText("进入Activity1");
        button.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent ( Activity2.this,Activity1.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG,"onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(savedInstanceState);
        Log.e(TAG,"onSaveInstanceState");
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.e(TAG,"onRestart");
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.e(TAG,"onPause");
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}