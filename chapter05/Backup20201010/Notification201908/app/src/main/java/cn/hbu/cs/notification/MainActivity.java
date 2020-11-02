package cn.hbu.cs.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                simpleNotify();
                break;
            case R.id.btn3:
                bigTextStyle();
                break;
            case R.id.btn4:
                inBoxStyle();
                break;
            case R.id.btn5:
                bigPictureStyle();
                break;
            case R.id.btn6:
                hangup();
                break;
            default:
                break;
        }
    }

    private void simpleNotify(){
        // 获取系统 通知管理 服务
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        // 构建 Notification
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("这里是通知的标题~")
                .setSmallIcon(R.drawable.ic_play)
                .setContentText("这里是通知的内容这里是通知的内容这里是通知的内容这里是通知的内容这里是通知的内容！");
        // 兼容  API 26，Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
            // 注册通道，注册后除非卸载再安装否则不改变
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId("AppTestNotificationId");
        }
        // 发出通知
        notificationManager.notify(1, builder.build());
    }

    private void bigTextStyle(){

        // 获取系统 通知管理 服务
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        // 构建 Notification
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("这里是bigTextStyle通知的标题~")
                .setSmallIcon(R.drawable.ic_play);
        Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle().bigText(
                "这里是bigTextStyle通知的内容！这里是bigTextStyle通知的内容！ " +
                        "这里是bigTextStyle通知的内容！这里是bigTextStyle通知的内容！ " +
                        "这里是bigTextStyle通知的内容！这里是bigTextStyle通知的内容！ " +
                        "这里是bigTextStyle通知的内容！这里是bigTextStyle通知的内容！" +
                        "这里是bigTextStyle通知的内容！这里是bigTextStyle通知的内容！ " +
                        "REF：https://my.oschina.net/Denua/blog/2052050 " +
                        "This is big text notification. This is big text notification. " +
                        "This is big text notification. This is big text notification. " +
                        "This is big text notification. This is big text notification. ");
        builder.setStyle(bigTextStyle);
        // 兼容  API 26，Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
            // 注册通道，注册后除非卸载再安装否则不改变
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId("AppTestNotificationId");
        }
        // 发出通知
        notificationManager.notify(1, builder.build());
    }

    public void inBoxStyle(){
        // 获取系统 通知管理 服务
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);// 构建 Notification
        builder.setContentTitle("这里是通知的标题~")
                .setSmallIcon(R.drawable.ic_play)
                .setContentText("这里是通知的内容！");
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle();

        inboxStyle.addLine("First line.");// 添加行
        inboxStyle.addLine("Second line");
        inboxStyle.addLine("Third line");
        inboxStyle.addLine("Last line");

        inboxStyle.setBigContentTitle("ContentTitle");// 设置标题以及简介文字
        inboxStyle.setSummaryText("SummaryText");

        builder.setStyle(inboxStyle);
        // 兼容  API 26，Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
            // 注册通道，注册后除非卸载再安装否则不改变
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId("AppTestNotificationId");
        }
        // 发出通知
        notificationManager.notify(1, builder.build());
    }

    public void bigPictureStyle(){
        // 获取系统 通知管理 服务
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        // 构建 Notification
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("这里是通知的标题~")
                .setSmallIcon(R.drawable.ic_play)
                .setContentText("这里是通知的内容！");
        // 构建一个 Style
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.cat));
        bigPictureStyle.setBigContentTitle("ContentTitle");// 设置标题
        bigPictureStyle.setSummaryText("SummaryText");// 设置副标题，简介文字
        bigPictureStyle.bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.cat));// 设置大图标
        builder.setStyle(bigPictureStyle);
        // 兼容  API 26，Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);// 注册通道，注册后除非卸载再安装否则不改变
            builder.setChannelId("AppTestNotificationId");
        }
        // 发出通知
        notificationManager.notify(1, builder.build());
    }

    private void hangup() {
        //https://blog.csdn.net/guolin_blog/article/details/79854070 郭霖
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "007";
            String channelName = "James Bond";
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            Notification notification = new NotificationCompat.Builder(this, "007")
                    .setContentTitle("横幅通知")
                    .setContentText("重要的信息，直接弹出来.")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.cat)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.cat))
                    .setAutoCancel(true)
                    .build();
            notificationManager.notify(1, notification);
        }
    }
}
