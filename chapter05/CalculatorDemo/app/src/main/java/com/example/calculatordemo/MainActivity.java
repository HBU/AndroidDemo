package com.example.calculatordemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

// Code by David: 2017.6
// Upgrade : Android Studio 3.0 ,Gradle 4.1 , David 2017.11
// Upgrade : Android Studio 3.2 ,Gradle 4.6 , David 2018.07
// Upgrade : Android Studio 3.5 ,Gradle 5.4 , David 2019.08 ; upgrade to AndroidX , David 2019.09
// Upgrade : Android Studio 4.0 ,Gradle 6.1 , David 2020.09
// Upgrade : Android Studio 4.2 ,Gradle 6.7 , David 2021.06

public class MainActivity extends AppCompatActivity {
    //变量定义
    private String operator;    //操作符：记录 + - * / 符号
    double n1 , n2 ,Result;     //操作数：操作符两端的数字，n1为左操作数，n2为右操作数。
    EditText editText;          //输入框：用于输入数字
    TextView textView;          //文本框：显示计算过程和计算结果
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;   //按钮：十个数字
    Button btnPlus,btnMinus,btnMultiply,btnDivide;              //按钮：加减乘除
    Button btnPoint,btnEqual,btnClear;                          //按钮：小数点，等号，清空
    String str; //临时变量

    private final View.OnClickListener onClickListener = new View.OnClickListener() {//侦听器
        @Override
        public void onClick(View view) {//点击事件
            editText = findViewById(R.id.editViewDavid);//与XML中定义好的EditText控件绑定
            textView = findViewById(R.id.textViewDavid);//与XML中定义好的TextView控件绑定
            editText.setCursorVisible(false);//隐藏输入框光标

            Button button = (Button)view;   //把点击获得的id信息传递给button
            DecimalFormat MyFormat = new DecimalFormat("###.##");//控制Double转为String的格式

            try{
                switch(button.getId())//获取点击按钮的ID，通过ID选择对应的选项执行
                {
                    case R.id.button1://如果点击了按钮：“1”
                    {
                        str = editText.getText().toString() + 1;
                        editText.setText(str);//输入框末尾，添加一个“1”
                        break;
                    }
                    case R.id.button2://2
                    {
                        str = editText.getText().toString() + 2;
                        editText.setText(str);
                        break;
                    }
                    case R.id.button3://3
                    {
                        str = editText.getText().toString() + 3;
                        editText.setText(str);
                        break;
                    }
                    case R.id.button4://4
                    {
                        str = editText.getText().toString() + 4;
                        editText.setText(str);
                        break;
                    }
                    case R.id.button5://5
                    {
                        str = editText.getText().toString() + 5;
                        editText.setText(str);
                        break;
                    }
                    case R.id.button6://6
                    {
                        str = editText.getText().toString() + 6;
                        editText.setText(str);
                        break;
                    }
                    case R.id.button7://7
                    {
                        str = editText.getText().toString() + 7;
                        editText.setText(str);
                        break;
                    }
                    case R.id.button8://8
                    {
                        str = editText.getText().toString() + 8;
                        editText.setText(str);
                        break;
                    }
                    case R.id.button9://9
                    {
                        str = editText.getText().toString() + 9;
                        editText.setText(str);
                        break;
                    }
                    case R.id.button0://0
                    {
                        str = editText.getText().toString();
                        //此处可以考虑添加代码，限制用户输入00,000等 2017.6.15
                        str = str + 0;
                        editText.setText(str);
                        break;
                    }
                    case R.id.buttonClear://Clear
                    {
                        editText.setText("");
                        textView.setText("");
                        Result = 0;
                        break;
                    }
                    case R.id.buttonPoint://.
                    {
                        str = editText.getText().toString();
                        if(str.contains(".")) //判断字符串中是否已包含小数点，如果有，就什么也不做
                        {
                            Log.e("David","empty");
                        }
                        else //如果没有小数点
                        {
                            if(str.equals("0"))//如果开始为0
                            {
                                str = "0" + ".";
                                editText.setText(str);
                            }
                            else if(str.equals(""))//如果初时显示为空，就什么也不做
                            {
                                Log.e("David","empty");
                            }
                            else
                            {
                                str = str + ".";
                                editText.setText(str);
                            }
                        }
                        break;
                    }
                    case R.id.buttonPlus://操作符+
                    {
                        str = editText.getText().toString();
                        n1 = Double.parseDouble(str);
                        operator = "+";
                        editText.setText("");
                        str = MyFormat.format(n1) + operator;
                        textView.setText(str);
                        break;
                    }
                    case R.id.buttonMinus://操作符-
                    {
                        str = editText.getText().toString();
                        n1 = Double.parseDouble(str);
                        operator = "-";
                        editText.setText("");
                        str = MyFormat.format(n1) + operator;
                        textView.setText(str);
                        break;
                    }
                    case R.id.buttonMultiply://操作符*
                    {
                        str = editText.getText().toString();
                        n1 = Double.parseDouble(str);
                        operator = "*";
                        editText.setText("");
                        str = MyFormat.format(n1) + operator;
                        textView.setText(str);
                        break;
                    }

                    case R.id.buttonDivide://操作符 /
                    {
                        str = editText.getText().toString();
                        n1 = Double.parseDouble(str);
                        operator = "/";
                        editText.setText("");
                        str = MyFormat.format(n1) + operator;
                        textView.setText(str);
                        break;
                    }
                    case R.id.buttonEqual://操作符 =
                    {
                        switch(operator)
                        {
                            case "+":
                                str = editText.getText().toString();
                                n2 = Double.parseDouble(str);
                                Result = n1 + n2;
                                str = MyFormat.format(n1) + operator + MyFormat.format(n2) + "=" + MyFormat.format(Result);
                                textView.setText(str);
                                str = MyFormat.format(Result)+"";
                                editText.setText(str);
                                break;
                            case "-":
                                str = editText.getText().toString();
                                n2 = Double.parseDouble(str);
                                Result = n1 - n2;
                                str = MyFormat.format(n1) + operator + MyFormat.format(n2) + "=" + MyFormat.format(Result);
                                textView.setText(str);
                                str = MyFormat.format(Result)+"";
                                editText.setText(str);
                                break;
                            case "*":
                                str = editText.getText().toString();
                                n2 = Double.parseDouble(str);
                                Result = n1 * n2;
                                str = MyFormat.format(n1) + operator + MyFormat.format(n2) + "=" + MyFormat.format(Result);
                                textView.setText(str);
                                str = MyFormat.format(Result)+"";
                                editText.setText(str);
                                break;
                            case "/":
                                str = editText.getText().toString();
                                n2 = Double.parseDouble(str);
                                if(n2 == 0)
                                {
                                    editText.setText("");
                                    textView.setText("除数不能为0");
                                    break;
                                }
                                else
                                {
                                    Result = n1 / n2;
                                    str = MyFormat.format(n1) + operator + MyFormat.format(n2) + "=" + MyFormat.format(Result);
                                    textView.setText(str);
                                    str = MyFormat.format(Result)+"";
                                    editText.setText(str);
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                    default:
                        break;
                }
            }catch(Exception e){
                Log.e("David","error");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取按钮的id
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btn0 = findViewById(R.id.button0);
        btnPlus =       findViewById(R.id.buttonPlus);
        btnMinus =      findViewById(R.id.buttonMinus);
        btnMultiply =   findViewById(R.id.buttonMultiply);
        btnDivide =     findViewById(R.id.buttonDivide);
        btnPoint =      findViewById(R.id.buttonPoint);
        btnEqual =      findViewById(R.id.buttonEqual);
        btnClear =      findViewById(R.id.buttonClear);
        //为按钮添加监听器
        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
        btn3.setOnClickListener(onClickListener);
        btn4.setOnClickListener(onClickListener);
        btn5.setOnClickListener(onClickListener);
        btn6.setOnClickListener(onClickListener);
        btn7.setOnClickListener(onClickListener);
        btn8.setOnClickListener(onClickListener);
        btn9.setOnClickListener(onClickListener);
        btn0.setOnClickListener(onClickListener);
        btnPlus.setOnClickListener(onClickListener);
        btnMinus.setOnClickListener(onClickListener);
        btnMultiply.setOnClickListener(onClickListener);
        btnDivide.setOnClickListener(onClickListener);
        btnPoint.setOnClickListener(onClickListener);
        btnEqual.setOnClickListener(onClickListener);
        btnClear.setOnClickListener(onClickListener);
    }
}
