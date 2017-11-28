package com.androidbook.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

public class NotificationExampleActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		notification(65);
	}

	public void notification(int progress) {
		// 获得Notification对象
		Notification nfo = new Notification(
				android.R.drawable.stat_sys_download, "开始", System.currentTimeMillis());
		// 初始化
		Intent intent = new Intent(getApplication(),NotificationExampleActivity.class);
		// 渲染自定义的布局文件
		nfo.contentView = new RemoteViews(getApplication().getPackageName(),
				R.layout.notification);
		// 进行自定义布局中TextView和ProgressBar的值的设置
		refreshState(nfo, "dddd", android.R.drawable.stat_sys_download,
				progress, "DOWNLOADING", intent);
		// 获得NotificationManager对象
		NotificationManager nnm = (NotificationManager) getApplication()
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// 发送通知
		nnm.notify(R.string.app_name, nfo);
	}

	// 获得自定义的布局文件经过渲染后的view，并对其相应值进行设置
	private void refreshState(Notification nfo, String tip, int icon,
			int progress, String text, Intent intent) {
		// 找到R.id.update_text 的TextView，并设置相应的值。
		nfo.contentView.setTextViewText(R.id.update_text, text);
		// 设置布局文件中R.id.update_progress的ProgressBar的值。
		nfo.contentView.setProgressBar(R.id.update_progress, 100, progress,false);
		nfo.icon = icon;
		nfo.tickerText = tip;
		nfo.when = System.currentTimeMillis();
		nfo.contentIntent = PendingIntent.getActivity(getApplication(),
				R.string.app_name, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

}