package cn.sleepycoder.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.NotificationCompat;

public class DownloadService extends Service {
    private Handler mHandler;
    private int progress = 0;
    private NotificationManager manger;
    private Runnable runnable;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler(getMainLooper());
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        runnable = new Runnable() {
            @Override
            public void run() {
                if(progress>99){
                    progress=0;
                    manger.cancel(MainActivity.TYPE_Progress);
                }else{
                    sendNotification();
                    progress++;
                    mHandler.postDelayed(runnable,500);
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null){
            return super.onStartCommand(intent, flags, startId);
        }
        int command = intent.getIntExtra("command",0);
        if(command==1){
            progress=0;
            mHandler.removeCallbacks(runnable);
            manger.cancel(MainActivity.TYPE_Progress);
        }else {
            if (progress < 1) {
                mHandler.post(runnable);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
    private void sendNotification(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.push));
        //禁止用户点击删除按钮删除
        builder.setAutoCancel(false);
        //禁止滑动删除
        builder.setOngoing(true);
        builder.setShowWhen(false);
        builder.setContentTitle("下载中..."+progress+"%");
        builder.setProgress(100,progress,false);
        //builder.setContentInfo(progress+"%");
        builder.setOngoing(true);
        builder.setShowWhen(false);
        Intent intent = new Intent(this,DownloadService.class);
        intent.putExtra("command",1);
        Notification notification = builder.build();
        manger.notify(MainActivity.TYPE_Progress,notification);

    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(runnable);
        manger.cancel(MainActivity.TYPE_Progress);
        super.onDestroy();
    }
}
