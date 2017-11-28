package com.androidbook.activitylife;

import com.androidbook.activitylife.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Activity3 extends Activity {
	
	/** Called when the activity is first created. */
	private static final String TAG = "Activity3";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        Log.e(TAG,"onCreate");
    }
	

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		 super.onStart();
		 Log.e(TAG,"onStart");
	}
    
	
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 Log.e(TAG,"onResume");
	}
    
    @Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		 Log.e(TAG,"onRestart");
	}
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 Log.e(TAG,"onPause");
	}
    @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 Log.e(TAG,"onStop");
	}
    
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 Log.e(TAG,"onDestroy");
	}
}