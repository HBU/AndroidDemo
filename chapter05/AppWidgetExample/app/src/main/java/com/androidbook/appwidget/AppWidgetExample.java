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

		// ֻ��ͨ��Զ�̶���������appwidget�еĿؼ�״̬
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
		// ���appwidget����ʵ�������ڹ���appwidget�Ա���и��²���
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		// �൱�ڻ�����б����򴴽���appwidget
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
		// ����appwidget
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
		// ����appWidget
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}

	private void update(Context context,RemoteViews remoteViews, String action, int id) {
		// ����һ��intent����
		Intent intent = new Intent(action);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// ���¼�
		remoteViews.setOnClickPendingIntent(id, pendingIntent);
	}
	
	//��widget��ɾ��ʱ�ص��÷���
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
	}

	//��widget����ʹ��ʱ�ص��÷���
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
	}

	//��widget������ʱ�ص��÷���
	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
	}

}
