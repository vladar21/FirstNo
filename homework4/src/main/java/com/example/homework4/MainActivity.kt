package com.example.homework4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var taskList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView
    private lateinit var editText: EditText
    private var count : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskList = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        listView = findViewById(R.id.listView)
        listView.adapter = adapter
        editText = findViewById(R.id.editText)

        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            var task = editText.text.toString()
            task = "${count++.toString()}) ${task}"
            if (task.isNotEmpty()) {
                taskList.add(task)
                adapter.notifyDataSetChanged()
                editText.text.clear()
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }

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
}
