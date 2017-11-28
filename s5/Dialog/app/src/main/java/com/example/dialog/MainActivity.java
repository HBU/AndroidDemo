package com.example.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import java.util.Calendar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");//初始为空，会传入空值
        textview = (TextView)findViewById(R.id.textView123);
        textview.setText(name);
    }
    public void onDialogClick1(View v){
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.alert_dark_frame)
                .setTitle("注意!!!")
                .setMessage("确定要退出么???")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();//Exit Activity
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).create().show();

    }

    public void onDialogClick2(View v){
        final String[] items = {"男","女","F","M"};

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("单选对话框")
                .setIcon(R.drawable.cat_small)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    public void onDialogClick3(View v){
        //准备数据源
        final String[] items1={"北京","上海","广州","深圳","天津","保定"};
        AlertDialog dialog = new AlertDialog
                .Builder(this)
                .setTitle("多选对话框")
                .setIcon(R.drawable.cat_small)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", null)
                .setMultiChoiceItems(items1, null, new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            //此处写点击事件代码
                        String itemStr = items1[which];
                        Toast.makeText(MainActivity.this, "选择了" + itemStr, Toast.LENGTH_SHORT).show();
                        // 关闭对话框
                        //dialog.dismiss();

                    }
                }).create();
        dialog.show();
    }

    public void onDialogClick4(View v){
        final String[] items1={"北京","上海","广州","深圳","天津","保定"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("单选对话框")
                .setIcon(R.drawable.cat_small)
                .setItems(items1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, items1[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    public void onDialogClick5(View v){
        //new Dialog  直接创建
        ProgressDialog pd1 = ProgressDialog.show(this, "提示", "正在登陆中", false, true);
    }

    public void onDialogClick6(View v){
        Calendar calendar = Calendar.getInstance();// import java.util.Calendar
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int monthOfyear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        //通过DatePickerDialog来创建日期选择对话框
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //当时间被设置后回调的方法
                Toast.makeText(MainActivity.this,
                        year + "年" + monthOfYear + "月" + dayOfMonth + "日",
                        Toast.LENGTH_SHORT).show();
            }
        }, year, monthOfyear, dayOfMonth );
        dpd.show();
    }

    public void onDialogClick7(View v){
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener( ) {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(MainActivity.this,
                                hourOfDay + ":" + minute + "!",
                                Toast.LENGTH_SHORT).show();
                    } },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true ).show();

    }

    public void onDialogClick8(View v){
        Dialog dialog = new Dialog(this);
        dialog.setTitle("拖动对话框");
        dialog.setContentView(R.layout.seek);
        SeekBar sbar = (SeekBar) dialog.findViewById(R.id.seekBar1);
        sbar.setMax(100);
        final TextView tview = (TextView) dialog.findViewById(R.id.tv_result);
        tview.setText("当前进度为："+sbar.getProgress());
        sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tview.setText("设置音量大小为："+seekBar.getProgress());
                textview = (TextView)findViewById(R.id.textView123);
                textview.setText(tview.getText());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dialog.show();
    }

    public void onDialogClick9(View v){
        MyDialog dialog = new MyDialog(MainActivity.this);
        dialog.show();
    }
    public void onDialogClick10(View v){
        Intent intent = new Intent(MainActivity.this,radiobutton.class);
        startActivity(intent);
    }



}

