package com.example.broadcastcustom20201125;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getAction().equals("com.example.broadcastcustom20201125.myBroadCast")) {
            //接收到广播，取出里面携带的数据
            String str = intent.getStringExtra("data");

            Log.e("David","接收到的广播的数据：" + str);
            Toast.makeText(context, "收到广播~", Toast.LENGTH_SHORT).show();
        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
