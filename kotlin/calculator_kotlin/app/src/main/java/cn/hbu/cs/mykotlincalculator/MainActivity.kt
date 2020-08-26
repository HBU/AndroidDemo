package cn.hbu.cs.mykotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {
    private var firstNumber = ""//第一个数字
    private var nextNumber = ""//第二个数字
    private var flag = ""//计算方式

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_c.setOnClickListener(this)
        btn_sign.setOnClickListener(this)
        btn_seven.setOnClickListener(this)
        btn_eight.setOnClickListener(this)
        btn_nine.setOnClickListener(this)
        btn_five.setOnClickListener(this)
        btn_four.setOnClickListener(this)
        btn_six.setOnClickListener(this)
        btn_three.setOnClickListener(this)
        btn_two.setOnClickListener(this)
        btn_one.setOnClickListener(this)
        btn_zreo.setOnClickListener(this)
        btn_multiplication.setOnClickListener(this)
        btn_subtract.setOnClickListener(this)
        btn_addition.setOnClickListener(this)
        btn_equal.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        doClick("" + (v as Button).text)
    }

    private fun  doClick(value:String){
        when(value){
            "+","-","*","÷"->{
                if (firstNumber?.isNotEmpty() && nextNumber?.isEmpty()){
                    flag = value
                }else if(firstNumber?.isNotEmpty() && nextNumber?.isNotEmpty()){
                    flag = value
                    doCount()
                }

            }
            "=" ->{
                if(firstNumber?.isNotEmpty() && nextNumber?.isNotEmpty()){
                    doCount()
                    flag = ""
                }
            }
            "C" ->{
                firstNumber = ""
                nextNumber = ""
                tv_show.text = ""
                flag = ""
            }
            else ->{
                if (flag?.isNotEmpty()){
                    if (nextNumber?.isEmpty() && value?.equals("0")){
                        Toast.makeText(this,"除数不能为0",Toast.LENGTH_SHORT).show()
                    }else{
                        nextNumber += value
                        tv_show.text = nextNumber
                    }
                }else{
                    firstNumber += value
                    tv_show.text = firstNumber
                }
            }
        }
    }

    /**
     * 计算方法
     */
    private fun doCount(){
        var result = 0.0
        when(flag){
            "+" -> result = firstNumber.toDouble()+nextNumber.toDouble()
            "-" -> result =firstNumber.toDouble()-nextNumber.toDouble()
            "*" -> result =firstNumber.toDouble()*nextNumber.toDouble()
            "÷" -> {
                result = firstNumber.toDouble()/nextNumber.toDouble()
            }
        }
        firstNumber = result.toString()
        nextNumber = ""
        tv_show.text = result.toString()
    }
}

