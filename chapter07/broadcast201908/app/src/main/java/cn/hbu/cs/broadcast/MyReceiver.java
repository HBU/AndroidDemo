package cn.hbu.cs.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        String Intent_Action = intent.getAction();
        if("cn.hbu.cs.MyReceiver".equals(Intent_Action)){
            Log.e("BroadcastReceiver","onReceive");
            Toast.makeText(context, "收到广播！", Toast.LENGTH_SHORT).show();
        }
    }
}
