package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback

const val EXTRA_CURRENT_COUNT = "EXTRA_CURRENT_COUNT"

class ActivityCounter : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_counter)
//    }

    private var count: Int = 0
    private lateinit var buttonUp: Button
    private lateinit var buttonClose: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
//        val user = User("test", "test")
        count = intent.getIntExtra(EXTRA_START_COUNT, 0)
        savedInstanceState?.let { count = it.getInt("count") }

        textView = findViewById(R.id.textView)
        textView.text = "$count"

        buttonUp = findViewById(R.id.button)
        buttonUp.setOnClickListener {
            count++
            textView.text = "$count"
        }

        buttonClose = findViewById(R.id.btnClose)
        buttonClose.setOnClickListener {
            saveCurrentCountAndSetResult()
        }

        class Callback: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                saveCurrentCountAndSetResult()
            }
        }
        onBackPressedDispatcher.addCallback(Callback())
    }

    private fun saveCurrentCountAndSetResult() {
        val intent = Intent()
        intent.putExtra(EXTRA_CURRENT_COUNT, count)
        setResult(RESULT_OK, intent)
        finish()
    }

//    override fun onBackPressed() {
//        saveCountAndFinish()
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
    }

}