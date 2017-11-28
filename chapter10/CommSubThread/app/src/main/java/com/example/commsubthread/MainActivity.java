package com.example.commsubthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Handler childHandler;
    Handler handler = new Handler(){//Handler在主线程,直接实例化。主线程启动时，已经有Looper和MessageQueue对象。
        @Override
        public void handleMessage(Message msg){
            Log.i("MThread", msg.obj+"，收到消息线程："+Thread.currentThread().getName());
            Log.i("MThread", "============================================================");
            super.handleMessage(msg);
        }
    };

    public void Button_OnClick(View view){
        new Thread1().start();

    }

    public void Button1_OnClick(View view){
        Message msg = Message.obtain();
        msg.what = 1;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        Date date = new Date(System.currentTimeMillis());
        msg.obj = formatter.format(date)+"";
        childHandler.sendMessage(msg);
        Log.i("MThread", msg.obj+"，发送消息线程："+Thread.currentThread().getName());
    }

    public void Button2_OnClick(View view){
        new Thread2().start();
    }
    public void Button3_OnClick(View view){
        new Thread3().start();
    }

    public class Thread1 extends Thread{
        @Override
        public void run() {
            Log.i("MThread", "启动进程："+Thread.currentThread().getName());
            Looper.prepare();
            childHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.i("MThread", msg.obj+"，收到消息线程："+Thread.currentThread().getName());
                    Log.i("MThread", "============================================================");
                }

            };
            Looper.loop();
        }
    }
    public class Thread2 extends Thread{
        @Override
        public void run() {
            Message msg = Message.obtain();
            msg.what = 1;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
            Date date = new Date(System.currentTimeMillis());
            msg.obj = formatter.format(date)+"";
            childHandler.sendMessage(msg);
            Log.i("MThread", msg.obj+"，发送消息线程："+Thread.currentThread().getName());
        }
    }

    public class Thread3 extends Thread{
        @Override
        public void run() {
            Message msg = Message.obtain();
            msg.what = 1;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
            Date date = new Date(System.currentTimeMillis());
            msg.obj = formatter.format(date)+"";
            handler.sendMessage(msg);
            Log.i("MThread", msg.obj+"，发送消息线程："+Thread.currentThread().getName());
        }
    }
}
