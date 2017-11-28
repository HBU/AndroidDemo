package com.androidbook.saveFile;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SaveFile extends Activity {
	
	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView = (TextView) findViewById(R.id.hello);
	}

	// File存储 - 保存文件
		public void saveFileInApplication() {
			try {
				// 以追加模式创建了file.txt文件，并写入文字"保存文件到应用程序下"
				FileOutputStream outStream = this.openFileOutput("file.txt", Context.MODE_APPEND);
				outStream.write("保存文件到应用程序下".getBytes());
				outStream.close();
				Toast.makeText(SaveFile.this, "保存文件成功", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				// 抛出异常
				Toast.makeText(SaveFile.this, "保存文件失败", Toast.LENGTH_LONG).show();
			}
		}

		// File存储 - 读取文件
		public void readFileInApplication() {
			try {
				FileInputStream inStream = this.openFileInput("file.txt");
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

		// File存储 - 删除文件
		public void deleteFileInApplication() {
			this.deleteFile("file.txt");
		}
}