package com.example.displaycalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Integer.toString
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {
    private lateinit var textView4: TextView
    private lateinit var textView5: TextView
    private lateinit var textView6: TextView
    private lateinit var textView7: TextView
    private lateinit var editText11: TextView
    private lateinit var editText13: EditText
    private lateinit var editText14: EditText
    private lateinit var button: Button

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView4 = findViewById(R.id.textView4)
        textView5 = findViewById(R.id.textView5)
        textView6 = findViewById(R.id.textView6)
        textView7 = findViewById(R.id.textView7)
        editText11 = findViewById(R.id.textView11)
        editText13 = findViewById(R.id.editText13)
        editText14 = findViewById(R.id.editText14)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            var metrics: DisplayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            var densityDpi = metrics.densityDpi

            val displayMetrics = resources.displayMetrics
            val widthInches = displayMetrics.widthPixels / displayMetrics.xdpi
            val heightInches = displayMetrics.heightPixels / displayMetrics.ydpi
            val diagonalInches = sqrt(widthInches * widthInches + heightInches * heightInches)


            editText11.text = "${diagonalInches}"
            editText13.setText(Integer.toString(displayMetrics.widthPixels))
//            editText13.setText(densityDpi)
            Log.i("MainActivity", "Text: ${editText13.text.toString()}")
            editText14.setText(displayMetrics.heightPixels.toString())

        }


    }
}