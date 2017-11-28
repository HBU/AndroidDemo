package com.androidbook.getContentProvider;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;


public class GetFromContentProvider extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 可以对客户端应用程序中的Topic表进行操作
	}
	
	// 对Topic表进行添加
	public void insertTopic(ContentValues values){
		Uri uri = Uri.parse("content://com.androidbook.client.contentprovider.DataProvider/Topic");
		getContentResolver().insert(uri, values);
	}
	
	// 对Topic表进行删除
	public void delete(String where, String[] selectionArgs){
		Uri uri = Uri.parse("content://com.androidbook.client.contentprovider.DataProvider/Topic");
		getContentResolver().delete(uri, where, selectionArgs);
	}
	
	// 对Topic表进行修改
	public void update(String where, String[] selectionArgs, ContentValues values){
		Uri uri = Uri.parse("content://com.androidbook.client.contentprovider.DataProvider/Topic");
		getContentResolver().update(uri, values, where, selectionArgs);
	}
	
	// 对Topic表进行查询
	public void query(String[] projection, String selection, String[] selectionArgs, String sortOrder, ContentValues values){
		Uri uri = Uri.parse("content://com.androidbook.client.contentprovider.DataProvider/Topic");
		getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
	}
}