package com.example.classwork7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

//        val itemDetailsTextView: TextView = findViewById(R.id.itemDetails)
//        itemDetailsTextView.text = intent.getStringExtra(MainActivity.DETAILS)

        intent.getStringExtra(MainActivity.DETAILS)?.let { details ->
                intent.getStringExtra(MainActivity.ID)?.let { id ->
                    DetailsFragment.newInstance(details, id)
                }
            }?.let {
                supportFragmentManager
                    .beginTransaction()
                    //                    .add(R.id.fragmentDetailsView, DetailsFragment.newInstance(details))
                    .replace(
                        R.id.fragmentDetailsView2,
                        it
                    )
                    .commit()
            }
    }
}