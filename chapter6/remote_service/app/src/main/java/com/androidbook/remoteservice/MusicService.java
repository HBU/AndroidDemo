package com.androidbook.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

public class MusicService extends Service{

	private MediaPlayer mediaPlayer;
	
	IServicePlayer.Stub  stub = new IServicePlayer.Stub() {
		
		@Override
		public void stop() throws RemoteException {
			mediaPlayer.stop();
		}
		
		@Override
		public boolean setLoop(boolean loop) throws RemoteException {
			return false;
		}
		
		@Override
		public void seekTo(int current) throws RemoteException {
			mediaPlayer.seekTo( current );
		}
		
		@Override
		public void play() throws RemoteException {
			mediaPlayer.start();
		}
		
		@Override
		public void pause() throws RemoteException {
			mediaPlayer.pause();
		}
		
		@Override
		public int getDuration() throws RemoteException {
			return mediaPlayer.getDuration();
		}
		
		@Override
		public int getCurrentPosition() throws RemoteException {
			return mediaPlayer.getCurrentPosition();
		}
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		
		return this.stub;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.mediaPlayer = MediaPlayer.create( this, R.raw.chengdu );
	}

}
