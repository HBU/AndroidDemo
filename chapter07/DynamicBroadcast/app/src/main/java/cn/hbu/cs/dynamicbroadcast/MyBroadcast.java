package cn.hbu.cs.dynamicbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("myBroadCastAction")) {
            //接收到广播，取出里面携带的数据
            String str = intent.getStringExtra("data");
            Log.e("David","接收到的广播的数据：" + str);
            Toast.makeText(context, "收到广播~", Toast.LENGTH_SHORT).show();
        }
    }
}
