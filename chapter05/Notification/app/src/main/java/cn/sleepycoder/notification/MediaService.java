package cn.sleepycoder.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import java.io.FileDescriptor;
import java.io.IOException;

public class MediaService extends Service {
    public static final int CommandPlay =1;
    public static final int CommandNext =2;
    public static final int CommandClose =3;
    public static final int StatusStop = 0;
    public static final int StatusPlay = 1;
    NotificationManager manger;
    MediaPlayer mediaPlayer;
    int[] songs = {R.raw.song1,R.raw.song2};
    int playerStatus = 0;
    private int index = 0;

    @Override
    public void onCreate() {
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null){
            return super.onStartCommand(intent, flags, startId);
        }
        int command = intent.getIntExtra("command",0);
        if(command==0 && mediaPlayer==null){
            command = CommandPlay;
        }
        if(command==CommandClose){
            playerStatus = StatusStop;
            manger.cancel(MainActivity.TYPE_Customer);
        }else {
            sendCustomerNotification(command);
        }
        setMediaPlayer(command, playerStatus, index);
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendCustomerNotification(int command){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Notification");
        builder.setContentText("自定义通知栏示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.push));
        builder.setAutoCancel(false);
        builder.setOngoing(true);
        builder.setShowWhen(false);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_template_customer);
        remoteViews.setTextViewText(R.id.title,"音乐播放器");
        remoteViews.setTextViewText(R.id.text,"歌曲"+index);
        if(command==CommandNext){
            remoteViews.setImageViewResource(R.id.btn1,R.drawable.ic_pause_white);
        }else if(command==CommandPlay){
            if(playerStatus==StatusStop){
                remoteViews.setImageViewResource(R.id.btn1,R.drawable.ic_pause_white);
            }else{
                remoteViews.setImageViewResource(R.id.btn1,R.drawable.ic_play_arrow_white_18dp);
            }
        }
        Intent Intent1 = new Intent(this,MediaService.class);
        Intent1.putExtra("command",CommandPlay);
        PendingIntent PIntent1 =  PendingIntent.getService(this,5,Intent1,0);
        remoteViews.setOnClickPendingIntent(R.id.btn1,PIntent1);

        Intent Intent2 = new Intent(this,MediaService.class);
        Intent2.putExtra("command",CommandNext);
        PendingIntent PIntent2 =  PendingIntent.getService(this,6,Intent2,0);
        remoteViews.setOnClickPendingIntent(R.id.btn2,PIntent2);

        Intent Intent3 = new Intent(this,MediaService.class);
        Intent3.putExtra("command",CommandClose);
        PendingIntent PIntent3 =  PendingIntent.getService(this,7,Intent3,0);
        remoteViews.setOnClickPendingIntent(R.id.btn3,PIntent3);

        builder.setContent(remoteViews);
        Notification notification = builder.build();
        manger.notify(MainActivity.TYPE_Customer,notification);
    }

    private void setMediaPlayer(int command,int status,int currentIndex){
        switch (command){
            case CommandPlay:
                if(status==StatusStop){
                    if(mediaPlayer==null){
                        mediaPlayer = MediaPlayer.create(this,songs[index%2]);
                    }
                    mediaPlayer.start();
                    playerStatus = StatusPlay;
                }else {
                    mediaPlayer.pause();
                    playerStatus = StatusStop;
                }
                break;
            case CommandNext:
                if(mediaPlayer!=null){
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }
                    mediaPlayer.reset();
                }
                index++;
                mediaPlayer = MediaPlayer.create(this,songs[index%2]);
                playerStatus = StatusPlay;
                mediaPlayer.start();

                break;
            case CommandClose:
                playerStatus = StatusStop;
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                break;
        }
    }

    @Override
    public void onDestroy() {
        if(mediaPlayer!=null){
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
