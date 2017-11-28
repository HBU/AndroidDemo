package com.example.asynctask_learning;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private Button btn_progress;
    private ProgressDialog progressDialog;
    private MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_progress = (Button) findViewById(R.id.btn_progress);
        btn_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCancelable(false);
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();//启动异步任务的处理
            }
        });

    }

    public class MyAsyncTask extends AsyncTask<Void,Integer,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog.show();//显示进度条对话框（更改UI组件）
            Log.e(TAG, Thread.currentThread().getName() + " onPreExecute ");
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.e(TAG, Thread.currentThread().getName() + " onProgressUpdate ");//通过publishProgress方法传过来的值进行进度条的更新.
            progressDialog.setProgress(values[0]);
        }
        @Override
        protected String doInBackground(Void... params) {
            Log.e(TAG, Thread.currentThread().getName() + " doInBackground Begin");
            for (int i = 0;i < 100; i ++){ //使用for循环来模拟进度条的进度.
                publishProgress(i);//调用publishProgress方法将自动触发onProgressUpdate方法来进行进度条的更新.
                try {
                    Thread.sleep(1000);//通过线程休眠模拟耗时操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, Thread.currentThread().getName() + " doInBackground End");
            return null;
        }
        @Override
        protected void onPostExecute(String string ){
            super.onPostExecute(string);
            Log.e(TAG, Thread.currentThread().getName() + " onPostExecute ");
            progressDialog.dismiss();//进度条消失（更改UI组件）
        }

    }
}
