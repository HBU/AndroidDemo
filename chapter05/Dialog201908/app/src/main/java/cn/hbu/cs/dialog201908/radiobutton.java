package cn.hbu.cs.dialog201908;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
/**
 * Created by David on 2017/9/11.
 */

public class radiobutton extends AppCompatActivity {
    private TextView myTextView;
    private RadioButton chinaBtn;
    private RadioButton ukBtn;
    RadioButton usaBtn;
    RadioButton gerBtn;
    RadioButton fraBtn;
    RadioGroup rg;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiobutton);
        //通过ID找到TextView
        myTextView = findViewById(R.id.myTextView);
        //通过ID找到RadioButton
        chinaBtn = findViewById(R.id.china_Button);
        ukBtn =  findViewById(R.id.fra_Button);
        //通过ID找到RadioGroup
        rg =  findViewById(R.id.rBtnGroup);
        //只要对RadioGroup进行监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if(R.id.china_Button == checkedId){
                    myTextView.setText("您选择的国家是：" + chinaBtn.getText().toString());
                }
                else if(R.id.uk_Button == checkedId){
                    myTextView.setText("您选择的国家是：" + ukBtn.getText().toString());
                }
                else if(R.id.usa_Button == checkedId){
                    myTextView.setText("您选择的国家是：" + usaBtn.getText().toString());
                }
                else if(R.id.ger_Button == checkedId){
                    myTextView.setText("您选择的国家是：" + gerBtn.getText().toString());
                }
                else if(R.id.fra_Button == checkedId){
                    myTextView.setText("您选择的国家是：" + fraBtn.getText().toString());
                }
            }
        });
    }
    public void onOK(View v){
        Intent intent = new Intent(radiobutton.this,MainActivity.class);
        intent.putExtra("name", myTextView.getText());
        startActivity(intent);
    }
}
