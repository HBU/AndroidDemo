package com.androidbook.saveSDCard;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class SaveSDcard extends Activity {

	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView = (TextView) findViewById(R.id.hello);
	}

	// SDCard存储 - 保存文件
	public void saveSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 获取SDCard目录
			File sdCardDir = Environment.getExternalStorageDirectory();
			// 根据sdCardDir的路径，创建SDCard.txt文件
			File saveFile = new File(sdCardDir, "SDCard.txt");
			try {
				// 新建一个FileOutputStream对象outStream，用true或者false来指定是否可以进行文件追加内容，默认为false
				FileOutputStream outStream = new FileOutputStream(saveFile,
						false);
				outStream.write("保存到SDCard根目录下".getBytes());
				outStream.close();
				Toast.makeText(SaveSDcard.this, "保存文件成功", Toast.LENGTH_LONG)
						.show();
			} catch (Exception e) {
				// 抛出异常
				Toast.makeText(SaveSDcard.this, "保存文件失败", Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	// SDCard存储 - 读取文件
	public void readSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 获取SDCard目录
			File sdCardDir = Environment.getExternalStorageDirectory();
			File saveFile = new File(sdCardDir, "SDCard.txt");

			try {
				FileInputStream inStream = new FileInputStream(saveFile);
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int length = -1;
				while ((length = inStream.read(buffer)) != -1) {
					stream.write(buffer, 0, length);
				}
				stream.close();
				inStream.close();
				textView.setText(stream.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				return;
			}
		}
	}

	// SDCard存储 - 删除文件
	public void deleteSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 获取SDCard目录
			File sdCardDir = Environment.getExternalStorageDirectory();
			File saveFile = new File(sdCardDir, "SDCard.txt");
			saveFile.delete();

		}
	}
}