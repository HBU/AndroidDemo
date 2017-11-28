package com.androidbook.getInfoFromContact;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;

public class GetInfoFromContact extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 可以对系统自带通讯录中的信息进行操作
	}

	// 对系统自带通讯录进行插入
	@SuppressLint("NewApi")
	public void insert(ContentValues values) {
		getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
	}

	// 对系统自带通讯录进行删除
	@SuppressLint("NewApi")
	public void delete(String where, String[] selectionArgs) {
		getContentResolver().delete(ContactsContract.Data.CONTENT_URI, where,
				selectionArgs);
	}

	// 对系统自带通讯录进行修改
	@SuppressLint("NewApi")
	public void update(String where, String[] selectionArgs,
			ContentValues values) {

		getContentResolver().update(ContactsContract.Data.CONTENT_URI, values,
				where, selectionArgs);
	}

	// 对系统自带通讯录进行查询并获取所有信息
	//@TargetApi(Build.VERSION_CODES.ECLAIR)
	@SuppressLint("NewApi")
	public void query(String[] projection, String selection,
					  String[] selectionArgs, String sortOrder, ContentValues values) {

		String name, email, remark, company, zipcode;
		
		// 指向一个用户表的光标
		//@SuppressLint("NewApi")
			Cursor systemPhoneCursor = getContentResolver().query(
				ContactsContract.Data.CONTENT_URI, projection, selection,
				selectionArgs, sortOrder);

		for (systemPhoneCursor.moveToFirst(); !systemPhoneCursor.isAfterLast(); systemPhoneCursor
				.moveToNext()) {
			// 获得联系人的id
			int id = systemPhoneCursor.getInt(systemPhoneCursor
					.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
			// 根据MIMETYPE字段不同来判别数据的类型
			String type = systemPhoneCursor.getString(systemPhoneCursor
					.getColumnIndex(ContactsContract.Data.MIMETYPE));
			// 获得联系人姓名
			name = systemPhoneCursor.getString(systemPhoneCursor
					.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
			// 判别type为邮箱的数据类型，获得联系人Email
			if (type.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {
				email = systemPhoneCursor
						.getString(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA1));

			} else if (type
					.equals(ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)) {
				// 判别type为备注的数据类型，获得联系人备注
				remark = systemPhoneCursor
						.getString(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));
			} else if (type
					.equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
				// 判别type为公司的数据类型， 获得联系人公司
				company = systemPhoneCursor
						.getString(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY));

			} else if (type
					.equals(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)) {
				// 判别type为地址邮编的数据类型， 获得联系人的地址与邮编
				// 获得国家
				String country = systemPhoneCursor
						.getString(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
				// 获得城市
				String city = systemPhoneCursor
						.getString(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
				// 获得街道
				String street = systemPhoneCursor
						.getString(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
				// 获得邮编
				zipcode = systemPhoneCursor
						.getString(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
				// 获得地址类型
				int addressType = systemPhoneCursor
						.getInt(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));

				switch (addressType) {
				// 为单位地址
				case ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK:

					break;
				// 为家庭地址
				case ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME:
					break;
				// 为其他地址
				case ContactsContract.CommonDataKinds.StructuredPostal.TYPE_OTHER:
					break;
				}
			} else if (type
					.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
				// 判别type为电话号码的数据类型，获得号码
				// 获得号码
				String phoneNumber = systemPhoneCursor
						.getString(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				// 获得号码的类型
				int phoneType = systemPhoneCursor
						.getInt(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
				switch (phoneType) {
				case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
					// 手机号码类型
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
					// 家庭电话
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
					// 单位号码
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
					// 其他号码
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK:
					// 传真号码
					break;
				}
			} else if (type
					.equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)) {

				// 判别type为头像的数据类型， 获得头像
				byte[] photoByte = systemPhoneCursor
						.getBlob(systemPhoneCursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO));
			}
		}
	}
}