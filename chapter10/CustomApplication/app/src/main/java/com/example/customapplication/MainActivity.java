package com.example.customapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    String TAG = "David - MainActivity";
    TextView tv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        //Log.d(TAG,"onCreate");
        tv = (TextView) findViewById(R.id.tv);
        et = (EditText) findViewById(R.id.et);
        tv.setText("共享数据：" + getApp().getTextData());

        findViewById(R.id.btnTextData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApp().setTextData(et.getText().toString());
                tv.setText("共享数据：" + et.getText().toString());
            }
        });
    }
    public void openMainActivity2(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }
    public customApp getApp() {
        return ((customApp)getApplicationContext());//取这个应用程序的Context
    }
}
