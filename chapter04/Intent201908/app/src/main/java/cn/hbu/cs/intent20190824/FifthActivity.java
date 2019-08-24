package cn.hbu.cs.intent20190824;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by David on 2017/10/16.
 */

public class FifthActivity extends Activity {
    public TextView textView ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        Button buttonGet = (Button) findViewById( R.id.getPara);
        Button buttonSend = (Button) findViewById( R.id.sendPara);
        textView = (TextView)findViewById(R.id.textPara) ;
        buttonGet.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String para = bundle.getString("参数1-2");
                textView.setText(para);

            }
        });
        buttonSend.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("参数2-1","参数值2-1");
                intent.putExtras(bundle);
                intent.setClass(FifthActivity.this,MainActivity.class);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }
}
