package com.example.classwork6_0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.Toast.*
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val list: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: FloatingActionButton = findViewById(R.id.floatingActionButton)

        list.add("First item")
        list.add("Second item")

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = adapter

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val popup = PopupMenu(this, view)
            popup.inflate(R.menu.listview_menu)
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.delete -> {
                        list.removeAt(position)
                        adapter.notifyDataSetChanged()
                    }
                }
                true
            }
            popup.show()
            true
        }

        button.setOnClickListener {
            val dialog = ItemDialog(object : ItemDialog.OnItemListener {
                override fun newItem(item: String) {
                    list.add(item)
                    adapter.notifyDataSetChanged()
                }
            })
            dialog.show(supportFragmentManager, "dialog")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_1 -> makeText(this, "First item", LENGTH_SHORT).show()
            R.id.item_2 -> makeText(this, "Second item", LENGTH_SHORT).show()
            R.id.subitem_1 -> makeText(this, "Sub item first", LENGTH_SHORT).show()
            R.id.subitem_2 -> makeText(this, "Sub item second", LENGTH_SHORT).show()
        }
        return true
    }
}