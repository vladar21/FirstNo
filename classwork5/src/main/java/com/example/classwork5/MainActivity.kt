package com.example.classwork5


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private val data: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i: Int in 1..5) {
            data.add("item $i")
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = ListAdapter(data, android.R.layout.simple_list_item_1)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = GridLayoutManager(this, 3)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
//            data.add("item ${data.size + 1}")
//            //adapter.notifyDataSetChanged()
//            adapter.notifyItemInserted(data.size-1)

//            val dataPicker = DatePickerDialog(this)
//
//            dataPicker.setOnDateSetListener { _, year, month, dayOfMonth ->
//                data.add("$year-$month-$dayOfMonth")
//
//                adapter.notifyItemInserted(data.size-1)
//            }
//
//            dataPicker.show()

//            val dateTime = LocalDateTime.now()
//            val timePickerDialog = TimePickerDialog(this,
//                { _, hourOfDay, minute ->
//                    data.add("$hourOfDay:${if (minute < 9) "0" else ""}$minute")
//                    adapter.notifyItemInserted(data.size-1)
//                },
//                dateTime.hour, dateTime.minute, true)
//            timePickerDialog.show()
            val listener: (dialog: DialogInterface, which: Int) -> Unit = { _, which ->
                when(which){
                    AlertDialog.BUTTON_NEGATIVE -> Toast.makeText(this, "Negative button clicked", Toast.LENGTH_SHORT)
                    AlertDialog.BUTTON_POSITIVE -> Toast.makeText(this, "Positive button clicked", Toast.LENGTH_SHORT)
                    AlertDialog.BUTTON_NEUTRAL -> Toast.makeText(this, "Neutral button clicked", Toast.LENGTH_SHORT)
                    else -> makeText(this, "Other button clicked", LENGTH_SHORT)
                }.show()
            }
            val textView = TextView(this)
            textView.text = "My message"
            val dialog = AlertDialog.Builder(this)
                .setTitle("Dialog Title")
//                .setItems(data.toTypedArray(), null)
//                .setMessage("Simple message")
//                .setMultiChoiceItems(data.toTypedArray(), null, null)
//                .setView(textView)
                .setView(R.layout.new_dialog)
                .setNegativeButton("Cancel", listener)
                .setNeutralButton("Misc", listener)
                .setPositiveButton("Ok", listener)
                .create()
            dialog.show()

        }



    }
}

data class ViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

class ListAdapter(private val data: List<String>,
                  private val resourceId: Int): RecyclerView.Adapter<ViewHolder>() {

    companion object {
        const val TAG: String = "MyAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView: TextView = LayoutInflater.from(parent.context)
            .inflate(resourceId, parent, false) as TextView
        Log.i(TAG, "onCreateViewHolder")
        return ViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "Bind $position")
        holder.textView.text = data[position]
    }

}

data class User(val name: String, val flagId: Int)

class UserAdapter(context: Context, users: List<User>) : ArrayAdapter<User>(
    context,
    0,
    users
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView =
            LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false) as TextView
        val user = getItem(position)
        user?.let {
            view.text = user.name
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.user_item_expand, parent, false)
        val textViewCountryName: TextView = view.findViewById(R.id.textViewCountryName)
        val imageVewFlag: ImageView = view.findViewById(R.id.imageViewFlag)
        val country = getItem(position)
        country?.let {
            textViewCountryName.text = it.name
            imageVewFlag.setImageResource(it.flagId)
        }

        return view
    }
}