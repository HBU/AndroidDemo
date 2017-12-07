package com.example.toastdemo;

import android.support.v7.app.AppCompatActivity;
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
        Toast.makeText(getApplicationContext(),"Toast默认样式",Toast.LENGTH_LONG).show();//Toast.LENGTH_LONG（3.5秒）
    }
    public void btnToast2(View v){
        Toast.makeText(this,"Toast默认样式",Toast.LENGTH_SHORT).show();        //Toast.LENGTH_SHORT（2秒）的值
    }
    public void btnToast3(View V){// 自定义Toast
        Toast toast = new Toast(this);// 创建Toast
        toast.setDuration(Toast.LENGTH_SHORT);// 设置Toast显示的时长
        ImageView img = new ImageView(this);// 创建ImageView
        img.setImageResource(R.mipmap.ic_launcher);// 设置图片的资源路径
        toast.setView(img);// 设置Toast的视图图片
        toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.CENTER,0,0); // 设置Toast显示在屏幕的位置
        toast.show();// 显示Toast
    }
    public void btnToast4(View V){
        Toast toast = new Toast(this);
        View layout = View.inflate(this,R.layout.toast,null);// 找到toast布局的位置
        toast.setView(layout);// 设置toast文本，把设置好的布局传进来
        toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.CENTER,0,0);// 设置土司显示在屏幕的位置
        toast.show();// 显示土司
    }


    public void btnToast5(View V){
        Toast toast=Toast.makeText(this, "可以设置时长的Toast", Toast.LENGTH_LONG);
        showMyToast(toast, 10*1000); //第一个参数：我们创建的Toast对象，第二个参数：我们想要设置显示的毫秒数
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
