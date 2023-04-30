package com.example.homework2

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.DisplayMetrics
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    val rightAnswers = arrayOf<String>("RB11", "RB22", "RB33", "RB44", "RB51")
    private lateinit var resultView : LinearLayout
    private lateinit var titleFIO : TextView
    private lateinit var fio: EditText
    private lateinit var titleOld : TextView
    private lateinit var old: EditText
    private lateinit var titleSalary : TextView
    private lateinit var salaryBar: SeekBar
    private lateinit var salaryBarInfo: TextView
    private var salary: Int = 0
    private lateinit var resultButton: Button
    private var radioGroup1: RadioGroup? = null
    private var radioGroup2: RadioGroup? = null
    private var radioGroup3: RadioGroup? = null
    private var radioGroup4: RadioGroup? = null
    private var radioGroup5: RadioGroup? = null
    private var radioButton1: RadioButton? = null
    private var radioButton2: RadioButton? = null
    private var radioButton3: RadioButton? = null
    private var radioButton4: RadioButton? = null
    private var radioButton5: RadioButton? = null
    private var experienceTitle: CheckBox? = null
    private var teamSkills: CheckBox? = null
    private var readyToTrip: CheckBox? = null
    var summaryTestResult : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var resultView = findViewById<LinearLayout>(R.id.resultViewLinearLayout)

        val titleFIO = findViewById<TextView>(R.id.textView)
        titleFIO.text = getString(R.string.fio)

        val fio = findViewById<EditText>(R.id.editText)
        fio.hint = getString(R.string.fioExample)

        val titleOld = findViewById<TextView>(R.id.textView1)
        titleOld.text = getString(R.string.old)

        val old : EditText = findViewById<EditText>(R.id.editText1)
        old.hint = getString(R.string.oldExample)

        val titleSalary = findViewById<TextView>(R.id.textView2)
        titleSalary.text = getString(R.string.salary)

        val salaryBar = findViewById<SeekBar>(R.id.seekBar)
        var salaryBarInfo = findViewById<TextView>(R.id.salaryBarInfo)

        salaryBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(salaryBar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                salaryBarInfo.text =  "${salaryBar.progress}$"

            }

            override fun onStartTrackingTouch(salaryBar: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(salaryBar: SeekBar) {
                // write custom code for progress is stopped
                salary = salaryBar.progress
            }
        })

        radioGroup1 = findViewById(R.id.radioGroup1)
        radioGroup2 = findViewById(R.id.radioGroup2)
        radioGroup3 = findViewById(R.id.radioGroup3)
        radioGroup4 = findViewById(R.id.radioGroup4)
        radioGroup5 = findViewById(R.id.radioGroup5)

        experienceTitle = findViewById<CheckBox>(R.id.checkBoxExperience)
        teamSkills = findViewById<CheckBox>(R.id.checkBoxTeamSkills)
        readyToTrip = findViewById<CheckBox>(R.id.checkBoxReadyToTrip)

        var resultTextViewValue = findViewById<TextView>(R.id.resultTextViewValue)

        resultButton = findViewById<Button>(R.id.resultButton)
        resultButton.setOnClickListener {

            var message : String? = ""
            var resultMessage : String? = ""
            var fioValue : String? = fio?.text.toString()
            var fioCheck : Boolean = fioValue!!.isBlank()
            if (fioCheck){
                message += "Введите свою фамилию, имя и отчество. "
            }

            var oldValueText : String? = old?.text.toString()
            var oldValueInt : Int? = oldValueText?.toIntOrNull()
            if (oldValueInt == null) {
                message += "Введите свой возраст. "
            }else{
                if (oldValueInt <= 16 || oldValueInt >= 65){
                    message += "Возраст должен быть в диапазоне от 16 дщ 65. "
                }
            }

            var salaryLevel : Int? = salaryBar.progress
            if (salaryLevel == 0 || salaryLevel == null){
                message += "Выберите уровень зарплаты. "
            }

            val selectedOption1: Int = radioGroup1!!.checkedRadioButtonId
            radioButton1 = findViewById(selectedOption1)
            val selectedOption2: Int = radioGroup2!!.checkedRadioButtonId
            radioButton2 = findViewById(selectedOption2)
            val selectedOption3: Int = radioGroup3!!.checkedRadioButtonId
            radioButton3 = findViewById(selectedOption3)
            val selectedOption4: Int = radioGroup4!!.checkedRadioButtonId
            radioButton4 = findViewById(selectedOption4)
            val selectedOption5: Int = radioGroup5!!.checkedRadioButtonId
            radioButton5 = findViewById(selectedOption5)

            if (message != ""){
                Toast.makeText(this@MainActivity,
                    message,
                    Toast.LENGTH_SHORT).show()
            }else if (oldValueInt != null) if (oldValueInt < 21 || oldValueInt > 40){
                resultMessage += "Не подходите по возрасту. "
            }else if (salaryLevel != null) if (salaryLevel < 800 || salaryLevel > 1600)
                resultMessage += "Не подходите по уровню ЗП" else{

                resultView.visibility = LinearLayout.VISIBLE

                if (radioButton1?.text == rightAnswers[0]){
                    summaryTestResult = summaryTestResult?.plus(2)
                }
                if (radioButton2?.text == rightAnswers[1]){
                    summaryTestResult = summaryTestResult?.plus(2)
                }
                if (radioButton3?.text == rightAnswers[2]){
                    summaryTestResult = summaryTestResult?.plus(2)
                }
                if (radioButton4?.text == rightAnswers[3]){
                    summaryTestResult = summaryTestResult?.plus(2)
                }
                if (radioButton5?.text == rightAnswers[4]){
                    summaryTestResult = summaryTestResult?.plus(2)
                }

                if (experienceTitle?.isChecked == true) {
                    summaryTestResult = summaryTestResult?.plus(2)
                }
                if (teamSkills?.isChecked == true) {
                    summaryTestResult = summaryTestResult?.plus(1)
                }
                if (readyToTrip?.isChecked == true) {
                    summaryTestResult = summaryTestResult?.plus(1)
                }
                var mess : String? = ""
                mess = "Ваш результат ${summaryTestResult.toString()}.";
                mess += if (summaryTestResult!! >= 10) {
                    " Тест сдан!"
                }else{
                    " Тест не сдан."
                }
                resultTextViewValue.text = mess
            }

        }

    }


}

