package com.example.playmedia20201117;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","onCreate executed");
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        this.mediaPlayer = MediaPlayer.create( this, R.raw.despacio);
        Log.d("MyService","mediaPlayer.start() executed");
        this.mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        Log.d("MyService","onDestroy executed");
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        this.mediaPlayer.stop();
        super.onDestroy();
    }
}