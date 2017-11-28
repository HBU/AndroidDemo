package com.androidbook.playmedia;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class Music extends Service{

	private MediaPlayer mediaPlayer;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.mediaPlayer = MediaPlayer.create( this, R.raw.sound);
		this.mediaPlayer.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		this.mediaPlayer.stop();
	}

}
