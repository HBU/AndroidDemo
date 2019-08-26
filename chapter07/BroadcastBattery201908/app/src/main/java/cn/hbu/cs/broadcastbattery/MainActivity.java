package cn.hbu.cs.broadcastbattery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_cell;
    private BatteryReceiver batteryReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_cell = findViewById(R.id.tv_cell);
        // 注册广播接收者java代码
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryReceiver = new BatteryReceiver();
        registerReceiver(batteryReceiver, intentFilter);// 注册receiver
    }
    /**     * 广播接收者     */
    private class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                int level = intent.getIntExtra("level", 0);
                tv_cell.setText("电池电量为" + level + "%");
                if (level < 15) {
                    tv_cell.setText("电池电量为" + level + "%" +"\n"+"电池电量不足15%，请及时充电!");
                    //Toast.makeText(MainActivity.this, "电池电量不足15%，请及时充电", Toast.LENGTH_SHORT).show();
                }
                if (level == 100) {
                    tv_cell.setText("电池电量为" + level + "%" +"\n"+"电池已充满！");
                    //Toast.makeText(MainActivity.this, "电池已充满！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryReceiver);
    }

}
