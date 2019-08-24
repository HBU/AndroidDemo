package cn.hbu.cs.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static final int TYPE_Normal = 1;
    public static final int TYPE_Progress = 2;
    public static final int TYPE_BigText = 3;
    public static final int TYPE_Inbox = 4;
    public static final int TYPE_BigPicture = 5;
    public static final int TYPE_Hangup = 6;
    public static final int TYPE_Media = 7;
    public static final int TYPE_Customer = 8;
    private NotificationManager manger;
    private Intent service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        service = new Intent(this,DownloadService.class);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                simpleNotify();
                break;
            case R.id.btn2:
                startService(service);//开启后台服务
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
            case R.id.btn7:
                mediaStyle();
                break;
            case R.id.btn8:
                Intent intent = new Intent(this,MediaService.class);
                startService(intent);
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
                .setContentText("这里是通知的内容！");
        // 兼容  API 26，Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
            // 注册通道，注册后除非卸载再安装否则不改变
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId("AppTestNotificationId");
        }
        // 发出通知
        notificationManager.notify(TYPE_Normal, builder.build());
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
                        "https://my.oschina.net/Denua/blog/2052050" +
                        "https://my.oschina.net/Denua/blog/2052050 " +
                        "https://my.oschina.net/Denua/blog/2052050 " +
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
        notificationManager.notify(TYPE_BigText, builder.build());
    }

    public void inBoxStyle(){
        // 获取系统 通知管理 服务
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        // 构建 Notification
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("这里是通知的标题~")
                .setSmallIcon(R.drawable.ic_play)
                .setContentText("这里是通知的内容！");
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
// 添加行
        inboxStyle.addLine("First line.");
        inboxStyle.addLine("Second line");
        inboxStyle.addLine("Third line");
        inboxStyle.addLine("Last line");

// 设置标题以及简介文字
        inboxStyle.setBigContentTitle("ContentTitle");
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
        notificationManager.notify(TYPE_Inbox, builder.build());
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
        Notification.BigPictureStyle bigPictureStyle =
                new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.cat));
// 设置标题
        bigPictureStyle.setBigContentTitle("ContentTitle");
// 设置副标题，简介文字
        bigPictureStyle.setSummaryText("SummaryText");
// 设置大图标
        bigPictureStyle.bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.cat));

        builder.setStyle(bigPictureStyle);
        // 兼容  API 26，Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
            NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
            // 注册通道，注册后除非卸载再安装否则不改变
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId("AppTestNotificationId");
        }
        // 发出通知
        notificationManager.notify(TYPE_BigPicture, builder.build());
    }

    private void hangup(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            Toast.makeText(MainActivity.this, "此类通知在Android 5.0以上版本才会有横幅有效！", Toast.LENGTH_SHORT).show();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("横幅通知");
        builder.setContentText("请在设置通知管理中开启消息横幅提醒权限");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification));
        Intent intent = new Intent(this,ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.setFullScreenIntent(pIntent,true);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        manger.notify(TYPE_Hangup,notification);
    }

    private void mediaStyle(){ // 函数失效，已被淘汰 2019.8.24
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentTitle("媒体类型");
//        builder.setContentText("歌曲名称");
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification));
//        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
//        Intent intent = new Intent(this,ImageActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
//        builder.setContentIntent(pIntent);
//        builder.addAction(R.drawable.ic_previous_white,"",null);
//        builder.addAction(R.drawable.ic_stop_white,"",null);
//        builder.addAction(R.drawable.ic_play_arrow_white_18dp,"",pIntent);
//        builder.addAction(R.drawable.ic_next_white,"",null);
//        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
//
//        style.setMediaSession(new MediaSessionCompat(this,"MediaSession",
//                new ComponentName(MainActivity.this,Intent.ACTION_MEDIA_BUTTON),null).getSessionToken());
//        //CancelButton在5.0以下的机器有效
//        style.setCancelButtonIntent(pIntent);
//        style.setShowCancelButton(true);
//        //设置要现实在通知右方的图标 最多三个
//        style.setShowActionsInCompactView(2,3);
//        builder.setStyle(style);
//        builder.setShowWhen(false);
//        Notification notification = builder.build();
//        manger.notify(TYPE_Media,notification);
    }

    @Override
    protected void onDestroy() {
        if(service!=null){
            stopService(service);
        }
        super.onDestroy();
    }
}
