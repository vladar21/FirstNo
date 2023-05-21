//package org.itstep.dialogsdemo
package com.example.classwork6_0

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class ItemDialog(var listener: OnItemListener? = null): DialogFragment() {

    interface OnItemListener {
        fun newItem(item: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.dialog_item, null, false)
        val editText: EditText = view.findViewById(R.id.editTextText)
        return AlertDialog.Builder(context)
            .setTitle("Item Dialog")
            .setView(view)
            .setNeutralButton("Cancel", null)
            .setPositiveButton("OK"
            ) { _, _ ->
                listener?.newItem(editText.text.toString())
            }
            .create()
    }
}