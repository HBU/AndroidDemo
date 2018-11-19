package com.example.broadcastcustom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by David on 2017/10/20.
 */

public class MyBroadcastReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.example.broadcastcustom.myBroadCast")) {
            //接收到广播，取出里面携带的数据
            String str = intent.getStringExtra("data");

            Log.e("David","接收到的广播的数据：" + str);
            Toast.makeText(context, "收到广播~", Toast.LENGTH_SHORT).show();
        }
    }
}