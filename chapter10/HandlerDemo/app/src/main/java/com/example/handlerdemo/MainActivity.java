package com.example.handlerdemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.text) ;
    }

    Handler handler = new Handler(){//Handler在主线程,直接实例化。主线程启动时，已经有Looper和MessageQueue对象。
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    Log.d("LogCat调用主线程handler:",message.toString());
                    textView.setText("直接使用主线程Handler\n更新UI~");
                    break;
                case 2:
                    Log.d("LogCat调用主线程handler:",message.toString());
                    textView.setText("子线程MyHandler发送消息\n调用主线程Handler\n更新UI~");
                    break;
                default:
                    break;
            }
            super.handleMessage(message);
        }
    };

    public void button1_onClick(View view){
        Message message = handler.obtainMessage();
        message.arg1= 0x01;
        message.arg2 = 0x02;
        message.obj = "objectType";
        message.what = 1;
        handler.sendMessage(message);//发送到消息队列
    }

    public void button2_onClick(View view){
        //new MyThread().start();
        MyThread myThread = new MyThread();
        myThread.start();
    }

    class MyThread extends Thread{
        public Handler MyHandler;//Handler在子线程
        @Override
        public void run(){
            Looper.prepare();//获取Looper和MessageQueue（Looper.prepare（）完成）
            MyHandler = new Handler(){//Handler实例化
                @Override
                public void handleMessage(Message message){//复写此方法，接收消息，作相应处理。
                    super.handleMessage(message);
                    if(message.what == 100)
                        handler.sendEmptyMessage(2);//子线程通过修改主线程的Handler更新UI
                        Log.d("LogCat调用子线程MyHandler:",message.toString());
                }
            };
            MyHandler.sendEmptyMessage(100);//发送到消息队列
            Looper.loop();//死循环获得消息队列变化，并分发消息，回调HandleMessage（）
        }//

    }

}
