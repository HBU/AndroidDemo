package com.example.customapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends Activity {
    String TAG = "David - MainActivity2";
    TextView tv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = (TextView) findViewById(R.id.tv);
        //Log.d(TAG,"onCreate");
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
    public customApp getApp() {
        return ((customApp)getApplicationContext());
    }
}
