package com.example.threaddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////////////////////////////////
        ///////第1种线程定义方法：继承Thread方式//////////////////////
        //////////////////////////////////////////////////////////////
        class MyThread extends Thread{
            @Override
            public void run(){
                Log.d("David:","MyThread");
            }
        }
        new MyThread().start();
        //////////////////////////////////////////////////////////////
        ///////第2种线程定义方法：继承Runnable接口方式////////////////
        //////////////////////////////////////////////////////////////
        class MyThread2 implements Runnable{
            @Override
            public void run() {
                Log.d("David:", "MyThread2");
            }
        }
        MyThread2 myThread2 = new MyThread2();
        new Thread(myThread2).start();
        //////////////////////////////////////////////////////////////
        ///////第3种匿名方式实现第2种方法（不定义新类，直接使用）/////
        //////////////////////////////////////////////////////////////
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("David","MyThread3");
            }
        }).start();
    }
}
