package com.example.updateuiinsubthread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//Ref:在子线程通知主线程更新UI的几种用法
//http://www.cnblogs.com/joy99/p/6121280.html

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvTest;
    private Button mBtnTest1;
    private Button mBtnTest2;
    private Button mBtnTest3;
    private Button mBtnTest4;
    private Button mBtnTest5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        mTvTest = (TextView) findViewById(R.id.tv_test);
        mBtnTest1 = (Button) findViewById(R.id.btn_test1);
        mBtnTest2 = (Button) findViewById(R.id.btn_test2);
        mBtnTest3 = (Button) findViewById(R.id.btn_test3);
        mBtnTest4 = (Button) findViewById(R.id.btn_test4);
        mBtnTest5 = (Button) findViewById(R.id.btn_test5);

        mBtnTest1.setOnClickListener(this);
        mBtnTest2.setOnClickListener(this);
        mBtnTest2.setOnClickListener(this);
        mBtnTest3.setOnClickListener(this);
        mBtnTest4.setOnClickListener(this);
        mBtnTest5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                new Thread(new Runnable() {//API 26,27 模拟器，此程序子线程能更新。。。API23,24崩溃。。。
                    @Override
                    public void run() {
                        mTvTest.setText("子线程真的可以更新UI吗？");
                    }
                }).start();
                break;
            case R.id.btn_test2:   //通过发送消息
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //mHandler.sendEmptyMessage(100);
                        Message message = new Message();
                        message.what = 100;
                        mHandler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.btn_test3:  //通过Handler.post方法
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTvTest.setText("handler.post");
                            }
                        });
                    }
                }).start();
                break;
            case R.id.btn_test4:  //通过 view.post方法
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mTvTest.post(new Runnable() {
                            @Override
                            public void run() {
                                mTvTest.setText("view.post");
                            }
                        });
                    }
                }).start();
                break;
            case R.id.btn_test5:  //通过 activity 的 runOnUiThread方法
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvTest.setText("runOnUIThread");
                            }
                        });
                    }
                }).start();
                break;

            default:
                break;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                mTvTest.setText("由Handler发送消息");
            }
        }
    };

}
