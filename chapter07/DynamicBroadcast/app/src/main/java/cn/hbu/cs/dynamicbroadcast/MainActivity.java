package cn.hbu.cs.dynamicbroadcast;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //创建广播接受类实例化对象
    MyBroadcast myBroadcast= new MyBroadcast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                IntentFilter intentFilter = new IntentFilter();
                //设置广播的名字（设置Action，可以添加多个要监听的动作）
                intentFilter.addAction("myBroadCastAction");
                //注册广播,传入两个参数， 实例化的广播接受者对象，intent 动作筛选对象
                registerReceiver(myBroadcast,intentFilter);
                //新建intent 对象
                Intent intent = new Intent();
                //设置 动作
                intent.setAction("myBroadCastAction");
                //添加传递的参数
                intent.putExtra("data", "Hi!I am broadcastData!");
                sendBroadcast(intent);
                Log.e("David","Broadcast sent !");
            }
        });

    }

    @Override
    protected void onPause() {
        //取消注册
        unregisterReceiver(myBroadcast);
        super.onPause();
    }

}
