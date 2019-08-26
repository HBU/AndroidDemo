package com.androidbook.broadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class BroadcastReceiverActivity extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String Intent_Action = intent.getAction();
		if("com.android.BroadcastReceiverDemo".equals(Intent_Action)){
			Log.e("BroadcastReceiver","onReceive");
            Toast.makeText(context, "收到广播！", Toast.LENGTH_SHORT).show();
		}
	}
}