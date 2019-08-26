package cn.hbu.cs.broadcast;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = super.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String Intent_Action = "com.android.BroadcastReceiverDemo";
                Intent intent = new Intent(Intent_Action);
                //Android8在静态广播的使用上做了一些限制
                //https://blog.csdn.net/yegshun/article/details/81232775
                intent.setComponent(new ComponentName("cn.hbu.cs.broadcast","cn.hbu.cs.broadcast.MyReceiver"));

                sendBroadcast(intent);
                Log.e("BroadcastReceiver","send broadcast");
            }
        });

    }
}