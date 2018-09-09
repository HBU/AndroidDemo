package com.androidbook.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidgetExample extends AppWidgetProvider {

	private final String UPDATE_ACTION_PREVIEW = "com.androidbook.appwidget.preview";
	private final String UPDATE_ACTION_NEXT = "com.androidbook.appwidget.next";

	@Override
	public void onReceive(Context context, Intent intent) {

		// 只能通过远程对象来设置appwidget中的控件状态
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
		// 获得appwidget管理实例，用于管理appwidget以便进行更新操作
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		// 相当于获得所有本程序创建的appwidget
		ComponentName componentName = new ComponentName(context,AppWidgetExample.class);
		if (intent.getAction().equals(UPDATE_ACTION_PREVIEW)) {
			remoteViews.setTextViewText(R.id.textView1, "Front");
			remoteViews.setImageViewResource(R.id.imageView1, R.drawable.ic_contact_list_picture);
		} else if (intent.getAction().equals(UPDATE_ACTION_NEXT)) {
			remoteViews.setTextViewText(R.id.textView1, "Next");
			remoteViews.setImageViewResource(R.id.imageView1, R.drawable.icon);
		} else {
			remoteViews.setTextViewText(R.id.textView1, "system automatic update broadcast");
			remoteViews.setImageViewResource(R.id.imageView1, R.drawable.ic_contact_list_picture);
		}
		// 更新appwidget
		appWidgetManager.updateAppWidget(componentName, remoteViews);
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
		update(context, remoteViews, UPDATE_ACTION_PREVIEW, R.id.btn_pre);
		update(context, remoteViews, UPDATE_ACTION_NEXT, R.id.btn_next);
		// 更新appWidget
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}

	private void update(Context context,RemoteViews remoteViews, String action, int id) {
		// 创建一个intent对象
		Intent intent = new Intent(action);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// 绑定事件
		remoteViews.setOnClickPendingIntent(id, pendingIntent);
	}
	
	//当widget被删除时回调该方法
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
	}

	//当widget可以使用时回调该方法
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
	}

	//当widget被禁用时回调该方法
	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
	}

}
