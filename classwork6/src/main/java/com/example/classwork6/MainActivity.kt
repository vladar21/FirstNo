package com.example.classwork6

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), RegistrationDialogListener {
    private lateinit var registrationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registrationTextView = findViewById(R.id.resultTextView)

        val registrationDialog = RegistrationDialog(this)
        registrationDialog.setRegistrationDialogListener(this)
        registrationDialog.show()
    }

    override fun onRegistrationMethodSelected(registrationMethod: String) {
        registrationTextView.text = "Выбран способ регистрации: $registrationMethod"
    }
}

interface RegistrationDialogListener {
    fun onRegistrationMethodSelected(registrationMethod: String)
}

class RegistrationDialog(context: Context) : Dialog(context) {
    private var listener: RegistrationDialogListener? = null

    fun setRegistrationDialogListener(listener: RegistrationDialogListener) {
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_dialog)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val okButton = findViewById<Button>(R.id.ok_button)

        okButton.setOnClickListener {
            val selectedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val selectedRegistrationMethod = selectedRadioButton.text.toString()
            // Вызываем метод интерфейса для передачи выбранного способа регистрации в активность
            listener?.onRegistrationMethodSelected(selectedRegistrationMethod)
            dismiss()
        }
    }
}