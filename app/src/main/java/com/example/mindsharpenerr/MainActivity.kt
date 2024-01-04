package com.example.mindsharpenerr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var toolbar:Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        var prsButton: Button = findViewById(R.id.button)
        var inpAns: EditText = findViewById(R.id.answer)
        var level:RadioGroup = findViewById(R.id.level)
        var pointGet: TextView = findViewById(R.id.pointnum)
        var value1: TextView = findViewById(R.id.val1)
        var value2: TextView = findViewById(R.id.val2)
        var opr: TextView = findViewById(R.id.operator)
        var pointObt = 0


        level.setOnCheckedChangeListener { buttonView, isChecked ->
            val lvl = when (level.checkedRadioButtonId) {
                R.id.i3 -> 1
                R.id.i5-> 2
                R.id.i7 -> 3
                else -> 0
            }
            value1.setText(getRandomNumber(lvl).toString())

            var randomOpt = (1..2).shuffled().last()

            if (randomOpt == 0) {
                opr.setText("+")
            } else if (randomOpt == 1) {
                opr.setText("-")
            } else if (randomOpt == 2) {
                opr.setText("*")
            } else{
                opr.setText("/")
            }
            value2.setText(getRandomNumber(lvl).toString())
        }


        prsButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if(verifyAnswer(opr.getText().toString(),
                        value1.getText().toString().toInt(),
                        value2.getText().toString().toInt(),
                        inpAns.getText().toString().toInt()
                )){
                    ++pointObt
                } else {
                    --pointObt
                }
                pointGet.setText("Point: "+ pointObt)
            }
        })
    }
    private fun getRandomNumber(level: Int): Int{
        var random = 0
        if(level == 1){
            random = (0..9).random()
        } else if(level == 2){
            random = (10..99).random()
        }else if(level == 3){
            random = (100..999).random()
        }else{
            random = 0
        }
        return random
    }
    private fun verifyAnswer(opr: String, inpAns: Int, value1: Int, value2: Int): Boolean{
        var ans = 0
        if(opr == "+"){
            ans = value1 + value2
        } else if(opr == "-"){
            ans = value1 - value2
        } else if(opr == "*"){
            ans = value1 * value2
        }else if(opr == "/"){
            ans = value1 / value2
        }
        if(inpAns == ans){
            return true
        } else{
            return false
        }
    }
}
