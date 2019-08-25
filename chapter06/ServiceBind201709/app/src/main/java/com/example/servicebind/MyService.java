package com.example.servicebind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {}
    // 服务创建的时候使用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","onCreate executed");
    }
    // 服务启动的时候调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }
    // 服务销毁的时候调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestroy executed");
    }
    ////////////////////////////////////////////////////////////////////
    private DownloadBinder mBinder = new DownloadBinder();
    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "开始下载！");
        }
        public int getProgress() {
            Log.d("MyService", "获取进度！");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


}
