package cn.hbu.cs.broadcastcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button =  findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                //设置广播的名字（设置Action）
                intent.setAction("com.example.broadcastcustom.myBroadCast");
                //携带数据
                intent.putExtra("data","Hello David");
                //Android8在静态广播的使用上做了一些限制
                //https://blog.csdn.net/yegshun/article/details/81232775
                intent.setComponent(new ComponentName("com.example.broadcastcustom","com.example.broadcastcustom.MyReceiver"));
                //发送广播（无序广播）
                sendBroadcast(intent);
                Log.e("David","Broadcast sent !");
            }
        });
    }
}
