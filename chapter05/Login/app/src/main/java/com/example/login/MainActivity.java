package com.example.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onLoginClick(View v){
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        //Toast.LENGTH_LONG（3.5秒）和Toast.LENGTH_SHORT（2秒）的值

    }
}
