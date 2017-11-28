package com.example.toastnotifacation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static android.app.PendingIntent.FLAG_ONE_SHOT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnToast1(View v){
        Toast.makeText(getApplicationContext(),"默认样式",Toast.LENGTH_LONG).show();
        //Toast.LENGTH_LONG（3.5秒）和Toast.LENGTH_SHORT（2秒）的值
    }
    public void btnToast2(View v){
        Toast.makeText(this,"默认样式",Toast.LENGTH_SHORT).show();
        //Toast.LENGTH_LONG（3.5秒）和Toast.LENGTH_SHORT（2秒）的值
    }
    public void btnToast3(View V){// 自定义土司
        // 创建土司
        Toast toast = new Toast(this);
        // 设置土司显示的时间长短
        toast.setDuration(Toast.LENGTH_SHORT);
        // 创建ImageView
        ImageView img = new ImageView(this);
        // 设置图片的资源路径
        img.setImageResource(R.mipmap.ic_launcher);
        // 设置土司的视图图片
        toast.setView(img);
        // 设置土司显示在屏幕的位置
        toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.CENTER,0,0);
        // 显示土司
        toast.show();
    }
    public void btnToast4(View V){
        Toast toast = new Toast(this);
        // 找到toast布局的位置
        View layout = View.inflate(this,R.layout.toast,null);
        // 设置toast文本，把设置好的布局传进来
        toast.setView(layout);
        // 设置土司显示在屏幕的位置
        toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.CENTER,0,0);
        // 显示土司
        toast.show();
    }

    public void btnToast5(View V){
        Toast toast=Toast.makeText(this, "这是可以随意设置时间的Toast", Toast.LENGTH_LONG);
        showMyToast(toast, 10*1000);
        //第一个参数为我们创建的Toast对象，第二个参数为我们想要设置显示的毫秒数
    }

    public void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }

    ////////////////////////////////////////////////////////
    //  Notification 的基本操作主要有:创建、更新、取消这三种。
    // 一个 Notification 的必要属性有三项，
    // 如果不设置则在运行时会抛出异常：
    //    小图标，通过 setSmallIcon() 方法设置
    //    标题，通过 setContentTitle() 方法设置
    //    内容，通过 setContentText() 方法设置
    //    除了以上三项，其它均为可选项。
    // 虽然如此，但还是应该给 Notification 设置一个 Action ，
    // 这样就可以直接跳转到 App 的某个 Activity 、启动一个 Service 或者发送一个 Broadcast。
    // 否则，Notification 仅仅只能起到通知的效果，而不能与用户交互。
    // 当系统接收到通知时，可以通过震动、响铃、呼吸灯等多种方式进行提醒。
    public void btnNotification0(View V){
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builder并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                //设置小图标
                .setSmallIcon(R.mipmap.ic_launcher_round)
                //设置通知标题
                .setContentTitle("我是最简单的Notification")
                //设置通知内容
                .setContentText("计算机科学与技术学院");
        //设置通知时间，默认为系统发出通知的时间，通常不用设置
        //.setWhen(System.currentTimeMillis());
        //通过builder.build()方法生成Notification对象,并发送通知,id=1
        notifyManager.notify(1, builder.build());
    }
}
