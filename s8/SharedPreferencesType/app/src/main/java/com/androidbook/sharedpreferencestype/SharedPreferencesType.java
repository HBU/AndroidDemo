package com.androidbook.sharedpreferencestype;

import com.androidbook.sharedpreferencestype.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;


public class SharedPreferencesType extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SharedPreferences sharedPreferences = getSharedPreferences("type", Context.MODE_APPEND);
		Editor editor = sharedPreferences.edit();
		editor.putString("String", "words");// String字符串型
     	editor.putBoolean("Boolean", true);// Boolean布尔型
		editor.putInt("Integer", 1);// Integer整型
		editor.putLong("Long", 1000000);// Long长整型
		editor.putFloat("Float", 3.5f);// Float浮点数型
		editor.commit();
	}
}