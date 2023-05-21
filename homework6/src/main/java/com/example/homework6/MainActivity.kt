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

class MainActivity : AppCompatActivity() {
    private var data: MutableList<Good> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = mutableListOf(
            Good( R.drawable.ireland, "FJDSFJ:SDFJ:SDLFKJ:SDLFJS:FLJS:FJS:DFSFS:F"),
            Good(R.drawable.usa, "Aenean placerat. In vulputate urna eu arcu. Aliquam erat volutpat. Suspendisse potenti. Morbi mattis felis at nunc."),
            Good(R.drawable.ua, "Nullam eget nisl. Donec vitae arcu. Nam quis nulla. Integer malesuada. In in enim a arcu imperdiet malesuada."),
            Good(R.drawable.uk, "Mauris tincidunt sem sed arcu. Nunc posuere. Nullam lectus justo, vulputate eget, mollis sed, tempor sed, magna."),
            Good(R.drawable.un, "Etiam neque. Curabitur ligula sapien, pulvinar a, vestibulum quis, facilisis vel, sapien."),
            Good(R.drawable.user1, "Morbi a metus. Phasellus enim erat, vestibulum vel, aliquam a, posuere eu, velit. Nullam sapien sem, ornare ac, nonummy non."),
            Good(R.drawable.user2, "Aenean placerat. In vulputate urna eu arcu. Aliquam erat volutpat. Suspendisse potenti. Morbi mattis felis at nunc."),
            Good(R.drawable.user3, "Maecenas ipsum velit, consectetuer eu, lobortis ut, dictum at, dui. In rutrum. Sed ac dolor sit amet purus."),
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = RecycleAdapter(data)
        {
//                good ->
//            val dialog = AlertDialog.Builder(this)
//                .setTitle(good.title)
//                .setMessage(good.fullText)
//                .setPositiveButton("OK", null)
//                .create()
//            dialog.show()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.notifyDataSetChanged()

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
                Toast.makeText(this,exitmenu, Toast.LENGTH_SHORT).show()
                moveTaskToBack(true);
                exitProcess(-1)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}

data class Good(val flagId: Int, val fullText: String)

class RecycleAdapter(private val goods: List<Good>, private val listener: (Good) -> Unit) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.good_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val good = goods[position]
        holder.imageView.setImageResource(good.flagId)
        holder.fullText.text = good.fullText

        holder.itemView.setOnClickListener {
            listener(good)
        }
    }

    override fun getItemCount(): Int {
        return goods.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullText: TextView = itemView.findViewById(R.id.fullText)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

}
