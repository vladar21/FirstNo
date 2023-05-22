package com.example.homework6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    var favorites: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
            Item(R.drawable.sample_1, "Lorem ipsum dolor sit amet. Sed voluptatum corporis aut fuga sapiente."),
            Item(R.drawable.sample_3, "Sit voluptatem impedit et voluptatem suscipit. Quasi est debitis quibusdam."),
            Item(R.drawable.sample_6, "Ut sunt internos ad expedita quisquam ea autem animi quo distinctio.")
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adapter = ItemAdapter(items, favorites)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
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
            R.id.cart -> {
                // Show cart action
                true
            }
            R.id.favorites -> {
//                val intent = Intent(this, FavoritesActivity::class.java)
//                intent.putExtra("favorites", ArrayList(favorites))
//                startActivity(intent)
                val intent = Intent(this@MainActivity, FavoritesActivity::class.java)
                intent.putParcelableArrayListExtra("favoritesList", ArrayList(favorites))
                startActivity(intent)
                true
            }

        }
        return super.onOptionsItemSelected(item)
    }

//    data class Item(val imageview: Int, val textView: String) : Parcelable {
//        constructor(parcel: Parcel) : this(
//            parcel.readInt(),
//            parcel.readString()!!
//        )
//
//        override fun writeToParcel(parcel: Parcel, flags: Int) {
//            parcel.writeInt(imageview)
//            parcel.writeString(textView)
//        }
//
//        override fun describeContents(): Int {
//            return 0
//        }
//
//        companion object CREATOR : Parcelable.Creator<Item> {
//            override fun createFromParcel(parcel: Parcel): Item {
//                return Item(parcel)
//            }
//
//            override fun newArray(size: Int): Array<Item?> {
//                return arrayOfNulls(size)
//            }
//        }
//    }

    class ItemAdapter(private val items: List<Item>, private val favorites: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.imageView.setImageResource(items[position].imageview)
            holder.textView.text = items[position].textView

            holder.itemView.setOnClickListener {
                val popup = PopupMenu(holder.itemView.context, holder.itemView)
                popup.inflate(R.menu.popup_menu)

                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.cart -> {
                            // Add to cart action
                            true
                        }
                        R.id.favorites -> {
                            val item = items[position]
                            favorites.add(item)
                            true
                        }
                        else -> false
                    }
                }

                popup.show()
            }
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




