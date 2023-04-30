package com.example.hometask2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val TAG = "=== MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onStart()
    {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume()
    {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause()
    {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop()
    {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(applicationContext, R.string.action_settings, Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_android_forever ->{
                Toast.makeText(applicationContext, R.string.android_forever, Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}