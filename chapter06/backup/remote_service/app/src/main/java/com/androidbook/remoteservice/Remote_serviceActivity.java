package com.androidbook.remoteservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Remote_serviceActivity extends Activity {
	//AIDL提供了一种非常简单的方式，
	// 让我们可以把一个进程内的对象或方法暴露给另一个程序使用，
	// 就好象另一个程序也拥有这些功能一样
	private SeekBar music_seekbar;
	private Button music_play;
	IServicePlayer iServicePlayer;
	private boolean isPlaying = false;
	private Handler handler = new Handler();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.initView();
        bindService(new Intent(this, MusicService.class), conn, Context.BIND_AUTO_CREATE);
		startService(new Intent(this, MusicService.class));
		handler.post( updateThread );
    }
    
	private void initView(){
    	this.music_seekbar = ( SeekBar )super.findViewById( R.id.music_seekbar );
    	this.music_play = ( Button )super.findViewById( R.id.music_play );
    	this.setListener();
    }
    
	private void setListener(){
		this.music_seekbar.setOnSeekBarChangeListener( new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if ( iServicePlayer != null ) {
					try {
						iServicePlayer.seekTo( seekBar.getProgress() );
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
    	});
    
    	this.music_play.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if ( !isPlaying ) {
					try {
						iServicePlayer.play();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					music_play.setText( "Pause" );
					isPlaying = true;
				}
				else {
					try {
						iServicePlayer.pause();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					music_play.setText( "Play" );
					isPlaying = false;
				}
			}
		});
	}
	
    private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			iServicePlayer = IServicePlayer.Stub.asInterface( service );
		}
	};
	
	private Runnable updateThread = new Runnable() {
		
		@Override
		public void run() {
			if ( iServicePlayer != null ) {
				try {
					music_seekbar.setMax( iServicePlayer.getDuration() );
					music_seekbar.setProgress( iServicePlayer.getCurrentPosition() );
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			handler.post( updateThread );
		}
	};
	
}