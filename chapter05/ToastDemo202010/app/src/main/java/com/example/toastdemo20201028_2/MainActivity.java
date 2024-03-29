package com.example.toastdemo20201028_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnToast1(View v){
        Toast.makeText(getApplicationContext(),"ToastLong",Toast.LENGTH_LONG).show();//Toast.LENGTH_LONG（3.5秒）
    }
    public void btnToast2(View v){
        Toast.makeText(this,"Toast默认样式",Toast.LENGTH_SHORT).show();        //Toast.LENGTH_SHORT（2秒）的值
    }
    public void btnToast3(View V){// 自定义Toast
        Toast toast = new Toast(this);// 创建Toast
        toast.setDuration(Toast.LENGTH_SHORT);// 设置Toast显示的时长
        ImageView img = new ImageView(this);// 创建ImageView
        img.setImageResource(R.drawable.qq2);// 设置图片的资源路径
        toast.setView(img);// 设置Toast的视图图片  ----
        // 「Deprecated」 表示该功能目前仍可以使用，但可能会在将来的 Android 版本中删除。建议开发人员避免长期使用此功能。
        //  2020.10.28 ：在 Android 11 Toast 的行为发生了变更 https://my.oschina.net/u/4588270/blog/4514488
        toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.CENTER,0,0); // 设置Toast显示在屏幕的位置
        toast.show();// 显示Toast
    }
    public void btnToast4(View V){
        Toast toast = new Toast(this);
        View layout = View.inflate(this,R.layout.toast,null);// 找到toast布局的位置
        toast.setView(layout);// 设置toast文本，把设置好的布局传进来
        toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.CENTER,0,0);// 设置Toast显示在屏幕的位置
        toast.show();// 显示土司
    }


    public void btnToast5(View V){
        Toast toast=Toast.makeText(this, "可以设置时长的Toast", Toast.LENGTH_LONG);
        showMyToast(toast, 100*1000); //第一个参数：我们创建的Toast对象，第二个参数：我们想要设置显示的毫秒数
    }

    public void showMyToast(final Toast toast, final int cnt) {
        //注意: 此方法在 API 24 有效，在 API 26+ 失效。新方法还需进一步研究。2017.12.7 edit by David。
        // 该方法创建Toast对象的时候时长因该设置为 Toast.LENGTH_LONG,因为该他的时长就是3秒,与下面的延时时间对应
        //cnt:需要显示的时长,毫秒
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);//每隔三秒调用一次show方法;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );//经过多长时间关闭该任务
    }
}
