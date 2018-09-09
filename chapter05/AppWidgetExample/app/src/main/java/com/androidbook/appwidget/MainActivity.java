package com.androidbook.appwidget;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	//https://blog.csdn.net/ljheee/article/details/70140310 设置启动项为nothing
	//在模拟器桌面长按鼠标，添加小部件
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.main);
	}

}
