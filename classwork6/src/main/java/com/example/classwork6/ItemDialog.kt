package com.example.classwork6

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ItemDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)

        return AlertDialog.Builder(context)
            .setTitle("Item Dialog")
            .setMessage("Enter new item")
            .setNeutralButton("Cancel", null)
            .create()
    }
}