package com.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.data;

public class MainActivity extends Activity {
    private Button ButtonCall;
    private Button ButtonMessage;
    private Button ButtonBrowser;
    private Button ButtonPhoto;
    private Button ButtonSetting;
    private Button ButtonDeskTop;
    private Button ButtonOtherActivity;
    private Button ButtonOtherActivity1;
    private Button ButtonOtherActivityParameter;
    private Button ButtonOtherActivityReturn;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //ref:http://www.jianshu.com/p/e65cb8d4e857
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.iReturn);
/////////////////////////////////////////////////////////////////////////
        ButtonCall=(Button)findViewById(R.id.iCall);
        ButtonCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("tel:10086");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                 }
        });
/////////////////////////////////////////////////////////////////////////
        ButtonMessage=(Button)findViewById(R.id.iMessage);
        ButtonMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("smsto:10086");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Hello");
                startActivity(intent);
            }
        });
/////////////////////////////////////////////////////////////////////////
        ButtonBrowser=(Button)findViewById(R.id.iBrowser);
        ButtonBrowser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("http://www.hbu.cn");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
/////////////////////////////////////////////////////////////////////////
        ButtonPhoto=(Button)findViewById(R.id.iPhoto);
        ButtonPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
/////////////////////////////////////////////////////////////////////////
        ButtonSetting=(Button)findViewById(R.id.iSetting);
        ButtonSetting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                 // 进入无线网络设置界面（其它可以举一反三）
                Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                startActivityForResult(intent, 0);
            }
        });
        /////////////////////////////////////////////////////////////////////////
        ButtonDeskTop=(Button)findViewById(R.id.iDesktop);
        ButtonDeskTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);// 添加Action属性
                intent.addCategory(Intent.CATEGORY_HOME);// 添加Category属性
                startActivity(intent);// 启动Activity
            }
        });
        /////////////////////////////////////////////////////////////////////////
        ButtonOtherActivity=(Button)findViewById(R.id.iOtherActivity);
        ButtonOtherActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //注意一定要把Activity注册到manifest文件
                Intent intent = new Intent( MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////////////////////
        ButtonOtherActivity1=(Button)findViewById(R.id.iOtherActivity1);
        ButtonOtherActivity1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //注意一定要把Activity注册到manifest文件
                Intent intent=new Intent("com.example.intent.ACTION_START");
                intent.addCategory("com.example.intent.MY_CATEGORY");
                startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////////////////////
        ButtonOtherActivityParameter=(Button)findViewById(R.id.iOtherActivityParameter);
        ButtonOtherActivityParameter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("University","HebeiUniversity");
                bundle.putString("College","Cyberspace Security & Computer");
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this,FourthActivity.class);
                startActivity(intent);

                //单个参数可用下面方法：
//                String s="网络空间安全与计算机学院";
//                Intent intent = new Intent( MainActivity.this,FourthActivity.class);
//                intent.putExtra("data", s);
//                startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////////////////////
        ButtonOtherActivityReturn=(Button)findViewById(R.id.iOtherActivityReturn);
        ButtonOtherActivityReturn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("参数1-2","参数值1-2");
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this,FifthActivity.class);
                startActivityForResult(intent,0);// 0 用于识别第二个页面返回值

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(resultCode == RESULT_OK )
                {
                    Bundle b = data.getExtras();
                    String string = b.getString("参数2-1");
                    textView.setText(string);
                }
                break;
            default:
                break;
        }
    }
}
