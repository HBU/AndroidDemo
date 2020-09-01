package cn.hbu.cs.mycalculatorkotlinfromjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var n1 = ""     //第一个数字
    private var n2 = ""     //第二个数字
    private var flag = ""   //标识

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button0.setOnClickListener(this)
        buttonPoint.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)
        buttonMultiply.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
        buttonPlus.setOnClickListener(this)
        buttonEqual.setOnClickListener(this)
        buttonClear.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        doClick("" + (v as Button).text)
    }

    private fun  doClick(value:String){
        when(value){
            "+","-","*","÷"->{
                if (n1?.isNotEmpty() && n2?.isEmpty()){
                    flag = value
                }else if(n1?.isNotEmpty() && n2?.isNotEmpty()){
                    flag = value
                    doCount()
                }
            }
            "=" ->{
                if(n1?.isNotEmpty() && n2?.isNotEmpty()){
                    doCount()
                    flag = ""
                }
            }
            "Clear" ->{
                n1 = ""
                n2 = ""
                textViewDavid.text = ""
                flag = ""
            }
            else ->{
                if (flag?.isNotEmpty()){
                    if (n2?.isEmpty() && value?.equals("0")){
                        Toast.makeText(this,"除数不能为0", Toast.LENGTH_SHORT).show()
                    }else{
                        n2 += value
                        textViewDavid.text = n2
                    }
                }else{
                    n1 += value
                    textViewDavid.text = n1
                }
            }
        }
    }

    private fun doCount(){
        var result = 0.0
        when(flag){
            "+" -> result = n1.toDouble()+n2.toDouble()
            "-" -> result = n1.toDouble()-n2.toDouble()
            "*" -> result = n1.toDouble()*n2.toDouble()
            "÷" -> result = n1.toDouble()/n2.toDouble()
        }
        n1 = result.toString()
        n2 = ""
        textViewDavid.text = result.toString()
    }
}