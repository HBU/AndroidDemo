package com.enmingx.asynctaskexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity {

    private EditText chronoValue;
    private TextView chronoText;
    private Button start;
    private EditText chronoValue1;
    private TextView chronoText1;
    private Button start1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 获取三个UI组件
        start = (Button)findViewById(R.id.start);
        chronoText = (TextView)findViewById(R.id.chronoText);
        chronoValue = (EditText)findViewById(R.id.chronoValue);
        start1 = (Button)findViewById(R.id.start1);
        chronoText1 = (TextView)findViewById(R.id.chronoText1);
        chronoValue1 = (EditText)findViewById(R.id.chronoValue1);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(String.valueOf(chronoValue.getText()));// 获取EditText里的数值
                if (value > 0) {// 验证数值是否大于零
                    new Chronograph().execute(value);
                }
                else {
                    Toast.makeText(AsyncTaskActivity.this, "请输入一个大于零的整数值 !", Toast.LENGTH_LONG).show();
                }
            }
        });

        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(String.valueOf(chronoValue1.getText()));// 获取EditText里的数值
                if (value > 0) {// 验证数值是否大于零
                    chronoText1.setText("等待了："+ value + "秒" );// 发布增量
                    MyClock(value);
                }
                else {
                    Toast.makeText(AsyncTaskActivity.this, "请输入一个大于零的整数值 !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class Chronograph extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            chronoValue.setEnabled(false);// 在计时开始前，先使按钮和EditText不能用
            start.setEnabled(false);
            chronoText.setText("0:0");
        }
        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = 0; i <= params[0]; i++) {// 计时
                for (int j = 0; j < 60; j++) {
                    try {
                        publishProgress(i, j);// 发布增量
                        if (i == params[0]) {
                            return null;
                        }
                        Thread.sleep(1000);// 暂停一秒
                        Log.d("DavidBack:",j+"");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (isCancelled()) {
                return null;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            chronoText.setText(values[0] + ":" + values[1]);// 更新UI界面
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            chronoValue.setEnabled(true);// 重新使按钮和EditText可以使用
            start.setEnabled(true);
        }
    }

    private void MyClock(Integer integer) {
            for (int i = 0; i <= integer; i++) {
                try {
                    Thread.sleep(1000);//  sleep不会引起ANR。
                    Log.d("DavidMain:",i+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

}
