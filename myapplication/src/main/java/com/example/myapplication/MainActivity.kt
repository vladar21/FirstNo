package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts

const val TAG = "MainActivity"

const val EXTRA_START_COUNT = "startCount"

class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
private lateinit var btnStartActivity: Button
    private lateinit var editTextStartNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextStartNumber = findViewById(R.id.editTextNumber)

        btnStartActivity = findViewById(R.id.btnStartActivity)

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { it ->
            Log.i(TAG, "onCreate: got result from second activity")
            if (it.resultCode == RESULT_OK) {
                val intent = it.data
                intent?.let { editTextStartNumber.setText("${it.getIntExtra(EXTRA_CURRENT_COUNT, 0)}") }
            }
        }

        btnStartActivity.setOnClickListener {
            Log.i(TAG, "onCreate: launch second activity")
            val intent = Intent(this, ActivityCounter::class.java)
            intent.putExtra(EXTRA_START_COUNT, editTextStartNumber.text.toString().toInt())
            //startActivity(intent)
            launcher.launch(intent)
        }
    }
}