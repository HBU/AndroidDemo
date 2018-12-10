package com.example.threadupdatetest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textview);
        Button button = (Button)findViewById(R.id.button);
        Button button1 = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setText("子线程可以更新UI???");//结论是不可以// onCreat（）子线程可以更改UI的具体原因：// Ref:http://www.cnblogs.com/xuyinhuan/p/5930287.html
            }
        }).start();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button://执行后，程序会立即崩溃，因为在子线程中更新UI是不允许的。
                new Thread(new Runnable() {//并没有崩溃，为什么呢？？
                    @Override
                    public void run() {
                        textView.setText("Nice to meet you");
                    }
                }).start();
                break;
            case R.id.button1:
                new Thread(new Runnable() {//并没有崩溃，为什么呢？？
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 100;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                textView.setText("由Handler发送消息");
            }
        }
    };
}
