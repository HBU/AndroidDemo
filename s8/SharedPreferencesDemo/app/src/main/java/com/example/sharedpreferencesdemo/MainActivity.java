package com.example.sharedpreferencesdemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button saveData ,getSaveData ,clearData;
    private EditText userName,password;

    public <T extends View> T $(int id) {//泛型。 Ref：http://blog.csdn.net/sbsujjbcy/article/details/42527957
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveData = $(R.id.saveData);
        getSaveData = $(R.id.getSaveData);
        clearData = $(R.id.clearData);
        userName = $(R.id.userName);
        password = $(R.id.password);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建名为“data”的文件，加入键值对，保存数据
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name",userName.getText().toString());
                editor.putString("password",password.getText().toString());
                //必须commit
                editor.commit();
            }
        });
        getSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取出文件中键值对，并提示出来
                SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                String name = preferences.getString("name","");
                String password = preferences.getString("password","");
                Toast.makeText(MainActivity.this,"UserName:"+name+"\nPassWord:"+password,Toast.LENGTH_LONG).show();
            }
        });

        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清空数据
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();
            }
        });



    }
}
