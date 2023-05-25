package com.example.homework6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class CartsActivity : AppCompatActivity() {
    var carts: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carts)

        carts = (intent.getSerializableExtra("cartsList") as? MutableList<Item>)!!

        if (carts != null) {
            val recyclerView: RecyclerView = findViewById(R.id.recyclerViewFavorites)
            val adapter = ItemAdapter(carts)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

            adapter.notifyDataSetChanged()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.cats_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val exitmenu : String = applicationContext.resources.getString(R.string.close_app_choice)
        when (item.itemId){
            R.id.exit -> {
                Toast.makeText(this,exitmenu, Toast.LENGTH_SHORT).show()
                moveTaskToBack(true);
                exitProcess(-1)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.imageView.setImageResource(items[position].imageview)
            holder.textView.text = items[position].textView
        }


        override fun getItemCount(): Int = items.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imageview)
            val textView: TextView = itemView.findViewById(R.id.textview)

            fun bind(item: Item) {
                imageView.setImageResource(item.imageview)
                textView.text = item.textView
            }
        }
    }
}