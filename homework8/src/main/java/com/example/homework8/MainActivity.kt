package com.example.homework8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var listView: ListView

    private val items = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        listView = findViewById(R.id.listView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        button.setOnClickListener {
            val item = editText.text.toString()
            items.add(item)
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }
    }

    override fun onStop() {
        super.onStop()

        val file = File(filesDir, "data.txt")

        if (!file.exists()) {
            file.createNewFile()
        }

        val writer = FileWriter(file)

        for (item in items) {
            writer.write(item + "\n")
        }

        writer.close()


    }

    override fun onStart() {
        super.onStart()

        val file = File(filesDir, "data.txt")

        if (file.exists()) {
            val reader = FileReader(file)

            val bufferedReader = BufferedReader(reader)
            var line: String? = bufferedReader.readLine()

            while (line != null) {
                items.add(line)
                line = bufferedReader.readLine()
            }

            bufferedReader.close()

            adapter.notifyDataSetChanged()
        }

    }


}