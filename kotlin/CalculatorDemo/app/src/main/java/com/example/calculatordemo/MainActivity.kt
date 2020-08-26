package com.example.calculatordemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

// Code by David: 2017.6
// Upgrade : Android Studio 3.0 ,Gradle 4.1 , David 2017.11
// Upgrade : Android Studio 3.2 ,Gradle 4.6 , David 2018.07
// Upgrade : Android Studio 3.5 ,Gradle 5.4 , David 2019.08； upgrade to AndroidX , David 2019.09
// Upgrade : Android Studio 4.0 ,Gradle 6.1 , David 2020.06； migrate to Kotlin，David 2020.08
class MainActivity : AppCompatActivity() {
    //变量定义
    private var operator //操作符：记录 + - * / 符号
            : String? = null
    var n1 = 0.0
    var n2 = 0.0
    var Result //操作数：操作符两端的数字，n1为左操作数，n2为右操作数。
            = 0.0
    var editText //输入框：用于输入数字
            : EditText? = null
    var textView //文本框：显示计算过程和计算结果
            : TextView? = null
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null
    var btn4: Button? = null
    var btn5: Button? = null
    var btn6: Button? = null
    var btn7: Button? = null
    var btn8: Button? = null
    var btn9: Button? = null
    var btn0 //按钮：十个数字
            : Button? = null
    var btnPlus: Button? = null
    var btnMinus: Button? = null
    var btnMultiply: Button? = null
    var btnDivide //按钮：加减乘除
            : Button? = null
    var btnPoint: Button? = null
    var btnEqual: Button? = null
    var btnClear //按钮：小数点，等号，清空
            : Button? = null
    var str //临时变量
            : String? = null
    private val onClickListener = View.OnClickListener { view ->

        //侦听器
        //点击事件
        editText = findViewById(R.id.editViewDavid) //与XML中定义好的EditText控件绑定
        textView = findViewById(R.id.textViewDavid) //与XML中定义好的TextView控件绑定
        editText.setCursorVisible(false) //隐藏输入框光标
        val button = view as Button //把点击获得的id信息传递给button
        val MyFormat = DecimalFormat("###.##") //控制Double转为String的格式
        try {
            when (button.id) {
                R.id.button1 -> {
                    str = editText.getText().toString() + 1
                    editText.setText(str) //输入框末尾，添加一个“1”
                }
                R.id.button2 -> {
                    str = editText.getText().toString() + 2
                    editText.setText(str)
                }
                R.id.button3 -> {
                    str = editText.getText().toString() + 3
                    editText.setText(str)
                }
                R.id.button4 -> {
                    str = editText.getText().toString() + 4
                    editText.setText(str)
                }
                R.id.button5 -> {
                    str = editText.getText().toString() + 5
                    editText.setText(str)
                }
                R.id.button6 -> {
                    str = editText.getText().toString() + 6
                    editText.setText(str)
                }
                R.id.button7 -> {
                    str = editText.getText().toString() + 7
                    editText.setText(str)
                }
                R.id.button8 -> {
                    str = editText.getText().toString() + 8
                    editText.setText(str)
                }
                R.id.button9 -> {
                    str = editText.getText().toString() + 9
                    editText.setText(str)
                }
                R.id.button0 -> {
                    str = editText.getText().toString()
                    //此处可以考虑添加代码，限制用户输入00,000等 2017.6.15
                    str = str + 0
                    editText.setText(str)
                }
                R.id.buttonClear -> {
                    editText.setText("")
                    textView.setText("")
                    Result = 0.0
                }
                R.id.buttonPoint -> {
                    str = editText.getText().toString()
                    if (str!!.contains(".")) //判断字符串中是否已包含小数点，如果有，就什么也不做
                    {
                        Log.e("David", "empty")
                    } else  //如果没有小数点
                    {
                        if (str == "0") //如果开始为0
                        {
                            str = "0" + "."
                            editText.setText(str)
                        } else if (str == "") //如果初时显示为空，就什么也不做
                        {
                            Log.e("David", "empty")
                        } else {
                            str = "$str."
                            editText.setText(str)
                        }
                    }
                }
                R.id.buttonPlus -> {
                    str = editText.getText().toString()
                    n1 = str!!.toDouble()
                    operator = "+"
                    editText.setText("")
                    str = MyFormat.format(n1) + operator
                    textView.setText(str)
                }
                R.id.buttonMinus -> {
                    str = editText.getText().toString()
                    n1 = str!!.toDouble()
                    operator = "-"
                    editText.setText("")
                    str = MyFormat.format(n1) + operator
                    textView.setText(str)
                }
                R.id.buttonMultiply -> {
                    str = editText.getText().toString()
                    n1 = str!!.toDouble()
                    operator = "*"
                    editText.setText("")
                    str = MyFormat.format(n1) + operator
                    textView.setText(str)
                }
                R.id.buttonDivide -> {
                    str = editText.getText().toString()
                    n1 = str!!.toDouble()
                    operator = "/"
                    editText.setText("")
                    str = MyFormat.format(n1) + operator
                    textView.setText(str)
                }
                R.id.buttonEqual -> {
                    when (operator) {
                        "+" -> {
                            str = editText.getText().toString()
                            n2 = str!!.toDouble()
                            Result = n1 + n2
                            str = MyFormat.format(n1) + operator + MyFormat.format(n2) + "=" + MyFormat.format(Result)
                            textView.setText(str)
                            str = MyFormat.format(Result) + ""
                            editText.setText(str)
                        }
                        "-" -> {
                            str = editText.getText().toString()
                            n2 = str!!.toDouble()
                            Result = n1 - n2
                            str = MyFormat.format(n1) + operator + MyFormat.format(n2) + "=" + MyFormat.format(Result)
                            textView.setText(str)
                            str = MyFormat.format(Result) + ""
                            editText.setText(str)
                        }
                        "*" -> {
                            str = editText.getText().toString()
                            n2 = str!!.toDouble()
                            Result = n1 * n2
                            str = MyFormat.format(n1) + operator + MyFormat.format(n2) + "=" + MyFormat.format(Result)
                            textView.setText(str)
                            str = MyFormat.format(Result) + ""
                            editText.setText(str)
                        }
                        "/" -> {
                            str = editText.getText().toString()
                            n2 = str!!.toDouble()
                            if (n2 == 0.0) {
                                editText.setText("")
                                textView.setText("除数不能为0")
                                break
                            } else {
                                Result = n1 / n2
                                str = MyFormat.format(n1) + operator + MyFormat.format(n2) + "=" + MyFormat.format(Result)
                                textView.setText(str)
                                str = MyFormat.format(Result) + ""
                                editText.setText(str)
                            }
                        }
                        else -> {
                        }
                    }
                }
                else -> {
                }
            }
        } catch (e: Exception) {
            Log.e("David", "error")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //获取按钮的id
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
        btn4 = findViewById(R.id.button4)
        btn5 = findViewById(R.id.button5)
        btn6 = findViewById(R.id.button6)
        btn7 = findViewById(R.id.button7)
        btn8 = findViewById(R.id.button8)
        btn9 = findViewById(R.id.button9)
        btn0 = findViewById(R.id.button0)
        btnPlus = findViewById(R.id.buttonPlus)
        btnMinus = findViewById(R.id.buttonMinus)
        btnMultiply = findViewById(R.id.buttonMultiply)
        btnDivide = findViewById(R.id.buttonDivide)
        btnPoint = findViewById(R.id.buttonPoint)
        btnEqual = findViewById(R.id.buttonEqual)
        btnClear = findViewById(R.id.buttonClear)
        //为按钮添加监听器
        btn1.setOnClickListener(onClickListener)
        btn2.setOnClickListener(onClickListener)
        btn3.setOnClickListener(onClickListener)
        btn4.setOnClickListener(onClickListener)
        btn5.setOnClickListener(onClickListener)
        btn6.setOnClickListener(onClickListener)
        btn7.setOnClickListener(onClickListener)
        btn8.setOnClickListener(onClickListener)
        btn9.setOnClickListener(onClickListener)
        btn0.setOnClickListener(onClickListener)
        btnPlus.setOnClickListener(onClickListener)
        btnMinus.setOnClickListener(onClickListener)
        btnMultiply.setOnClickListener(onClickListener)
        btnDivide.setOnClickListener(onClickListener)
        btnPoint.setOnClickListener(onClickListener)
        btnEqual.setOnClickListener(onClickListener)
        btnClear.setOnClickListener(onClickListener)
    }
}