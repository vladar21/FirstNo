package com.example.homework3

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var equOnScreen: String? = null
    private var operator : String = ""
    private lateinit var resultOnScreen : TextView

    override fun onCreateOptionsMenu (menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate (R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val exitmenu : String = applicationContext.resources.getString(R.string.close_app_choice)
        when (item.itemId){
            R.id.exit -> {
                Toast.makeText(this,exitmenu,Toast.LENGTH_SHORT).show()
                moveTaskToBack(true);
                exitProcess(-1)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        val buttonPlusMinus = findViewById<Button>(R.id.buttonPlusMinus)
        val buttonDot = findViewById<Button>(R.id.buttonDot)
        val buttonModuleDivide = findViewById<Button>(R.id.buttonModuleDivide)
        val buttonEqual = findViewById<Button>(R.id.buttonEqual)

        resultOnScreen = findViewById<TextView>(R.id.resultOnScreen)

        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        buttonPlus.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
        buttonMultiply.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)
        buttonClear.setOnClickListener(this)
        buttonPlusMinus.setOnClickListener(this)
        buttonModuleDivide.setOnClickListener(this)
        buttonDot.setOnClickListener(this)
        buttonEqual.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        val message: String = applicationContext.resources.getString(R.string.information_message)
        if(equOnScreen.isNullOrBlank() || equOnScreen == message) {
            equOnScreen = ""
        }

        when (p0?.id) {
            R.id.button0 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "0"
            }
            R.id.button1 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "1"
            }
            R.id.button2 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "2"
            }
            R.id.button3 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "3"
            }
            R.id.button4 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "4"
            }
            R.id.button5 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "5"
            }
            R.id.button6 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "6"
            }
            R.id.button7 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "7"
            }
            R.id.button8 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "8"
            }
            R.id.button9 -> {
                if (operator == "="){
                    equOnScreen = ""
                    operator = ""
                }
                equOnScreen = "$equOnScreen" + "9"
            }
            R.id.buttonPlus -> {
                equOnScreen = removePreviousOperator(equOnScreen.toString(), operator) + "+"
                operator = "\\+"
            }
            R.id.buttonMinus -> {
                equOnScreen = removePreviousOperator(equOnScreen.toString(), operator) + "-"
                operator = "\\-"
            }
            R.id.buttonMultiply -> {
                equOnScreen = removePreviousOperator(equOnScreen.toString(), operator) + "*"
                operator = "\\*"
            }
            R.id.buttonDivide -> {
                equOnScreen = removePreviousOperator(equOnScreen.toString(), operator) + "/"
                operator = "\\/"
            }
            R.id.buttonPlusMinus -> {
                if (operator.isEmpty() || operator == "+/-"){
                    if (equOnScreen!![0] == '-'){
                        equOnScreen = equOnScreen!!.drop(1)
                    }else{
                        equOnScreen = "-$equOnScreen"
                    }
//                operator = ""
                    resultOnScreen.text = equOnScreen
                }

            }
            R.id.buttonModuleDivide -> {
                equOnScreen = removePreviousOperator(equOnScreen.toString(), operator) + "%"
                operator = "\\%"
            }
            R.id.buttonDot -> {
//                equOnScreen = removePreviousOperator(equOnScreen.toString(), operator) + "."
                val matches : String = equOnScreen.toString().filter { it -> it == '.' }
                if (operator.isEmpty() && matches.isEmpty()) {
                    equOnScreen = equOnScreen.toString() + "."
                }else if (operator.isNotEmpty() && matches.length == 1){
                    equOnScreen = equOnScreen.toString() + "."
                }else if (operator.isEmpty() && matches.length == 1){
                    equOnScreen = equOnScreen.toString().replace(".", "")
                }else if(operator.isNotEmpty() && matches.length >= 2){
                    equOnScreen = equOnScreen.toString().replace(".", "")
                }else if(operator == "="){
                    equOnScreen = "."
                }
                else{
                    equOnScreen = equOnScreen.toString() + "."
                }

//                equOnScreen = removePreviousOperator(equOnScreen.toString(), operator) + "."
//                operator = "\\."
            }
            R.id.buttonClear ->  equOnScreen = ""
            R.id.buttonEqual -> {
//                equOnScreen = removePreviousOperator(equOnScreen.toString(), operator)
                var temp : Double? = stringdataIntoCalc(equOnScreen!!, operator)
                equOnScreen = if(temp.toString() != null) {
                    temp.toString()
                } else message
                operator = ""
            }
        }
        resultOnScreen.text = equOnScreen

    }

    fun removePreviousOperator(cl : String, op : String) : String {
        val nop = op.replace("\\", "")
        var result = "";
        if (cl[0] == '-'){
            result = cl.drop(1)
            result = result.replace(nop, "")
            result = "-$result"
        }else{
            result = cl.replace(nop, "")
        }

        return result
    }

    fun stringdataIntoCalc(string : String, operator : String) : Double? {
        val rx = operator.toRegex()
        var stringList : MutableList<String> = splitKeepDelims(string,rx)
        if(stringList.size < 2) {return null}
        var first : String = stringList[0]
        var second : String = stringList[2]
        var sign : String = stringList[1]
        var result : Double? = null

        when(sign) {
            "+" -> result = first.toDouble() + second.toDouble()
            "-" -> result = first.toDouble() - second.toDouble()
            "*" -> result = first.toDouble() * second.toDouble()
            "/" -> result = first.toDouble() / second.toDouble()
            "%" -> result = first.toDouble() % second.toDouble()
        }

        if(result != null) {
            return result
        }
        return null
    }

    private fun splitKeepDelims(temp: String, rx: Regex, keep_empty: Boolean = true) : MutableList<String> {
        var isMinus : Char? = temp[0]
        var s = temp
        if (isMinus == '-'){
            s = s.drop(1)
        }
        var res = mutableListOf<String>()  // Объявляем изменяемый список
        var start = 0                      // Задаём переменную для начала подстроки
        rx.findAll(s).forEach {             // Ищем все совпадения
            val substr_before = s.substring(start, it.range.first()) // Подстрока до начала совпадения
            if (substr_before.isNotEmpty() || keep_empty) {
                res.add(substr_before)      // Добавляем подстроку до начала совпадения
            }
            res.add(it.value)               // Добавляем совпадение
            start = it.range.last()+1       // Обновляем начало следующей подстроки до совпадения
        }
        if ( start != s.length ) res.add(s.substring(start)) // Если есть текст после последнего совпадения, добавляем
        if (isMinus == '-'){
            var el : String = res[0]
            res[0] = "-$el"
        }
        return res
    }
}