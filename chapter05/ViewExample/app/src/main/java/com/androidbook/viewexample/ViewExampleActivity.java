package com.androidbook.viewexample;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewExampleActivity extends Activity {
    	
	private Button button1;
	private EditText editText1;
	private TextView textView1;
	private ImageView imageView1;
	
	private boolean flag = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main_test);
        setContentView(R.layout.main);
        textView1 = (TextView) findViewById(R.id.textView1);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if(flag) {
					imageView1.setImageResource(R.drawable.dog);
					button1.setText("A-->B");
				} else {
					imageView1.setImageResource(R.drawable.cat_normal);
					button1.setText("B-->A");
				}
				flag = !flag;
			}
		});

        editText1 = (EditText) findViewById(R.id.editText1);
        editText1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				textView1.setText(s.toString());
			}
		});

        
    }
}