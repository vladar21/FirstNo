package com.example.classwork7

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.classwork7.data.DummyItem


class MainActivity : AppCompatActivity() {
    private val list: List<DummyItem> = DummyItem.getList()

    companion object {
        const val DETAILS: String = "DETAILS"
        const val ID: String = "ID"
    }

    private var isTwoPanel: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view: View? = findViewById(R.id.fragmentDetailsView)
        isTwoPanel = view != null

        val listView: ListView = findViewById(R.id.itemList)
        listView.adapter = DummyListAdapter(this, list)

        listView.setOnItemClickListener { _, _, position, _ ->
            val details = list[position].details
            if(isTwoPanel) {
                // work with fragment
                supportFragmentManager
                    .beginTransaction()
//                    .add(R.id.fragmentDetailsView, DetailsFragment.newInstance(details))
                    .replace(R.id.fragmentDetailsView, DetailsFragment.newInstance(details, list[position].id))
                    .commit()
            } else {
                // work with activity
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra(DETAILS, details)
                intent.putExtra(ID, list[position].id)
                startActivity(intent)
            }
        }
    }
}

class DummyListAdapter(context: Context, list: List<DummyItem>): ArrayAdapter<DummyItem>(context, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        getItem(position)?.let {
            view.text = it.content
        }
        return view
    }
}