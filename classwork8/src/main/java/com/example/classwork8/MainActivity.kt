package com.example.classwork8

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.core.content.contentValuesOf
import java.io.File
import java.io.PrintStream

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // internal storage
        val file: File = File(filesDir, "myfile.txt")
        Log.i(TAG, "FilesDir: ${file.absolutePath}")

//        writeToInternalFileDemo()
//        readFroInternalFileDemo(file)
//        writeToCacheDirDemo()
//        externalStorageDemo()
//        readWriteExternalPublicStoragesDemo()
//        savePreferencesDemo()
//        readPreferencesDemo()

        val db = DbHelper(this)
        saveDataToDbDemo(db)
//        updateDataDemo(db)
//        readDataDemo(db)
        val listView = findViewById<ListView>(R.id.todoListView)
        val cursor = db.readableDatabase.query(
            "todolist",
            arrayOf("_id", "task", "priority"),
            "priority > ?", arrayOf("5"),
            null, null, "priority desc"
        )
        listView.adapter = SimpleCursorAdapter(
            this,
            R.layout.list_item, cursor, arrayOf("_id", "task", "priority"),
            intArrayOf(R.id.textViewId, R.id.textViewTask, R.id.textViewPriority), 0
        )
    }

    private fun readDataDemo(db: DbHelper) {
        val readableDatabase = db.readableDatabase
        val cursor = readableDatabase.query(
            "todolist",
            arrayOf("id", "task", "priority"),
            "priority > ?", arrayOf("5"),
            null, null, null
        )
        while (cursor.moveToNext()) {
            Log.i(
                TAG,
                "onCreate: ${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)}"
            )
        }
    }

    private fun updateDataDemo(db: DbHelper) {
        val writableDatabase = db.writableDatabase
        val updateValues = ContentValues()
        updateValues.put("priority", 15)
        writableDatabase.update("todolist", updateValues, "id = ?", arrayOf("1"))
    }

    private fun saveDataToDbDemo(db: DbHelper) {
        val writableDatabase = db.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("task", "Learn Android")
        contentValues.put("description", "Cool to be Android dev")
        contentValues.put("priority", 10)
        writableDatabase.insert("todolist", null, contentValues)
    }

    private fun readPreferencesDemo() {
        val isDarkMode = getPreferences(MODE_PRIVATE).getBoolean("DarkMode", false)
        Log.i(TAG, "onCreate: DarkMode = $isDarkMode")
    }

    private fun savePreferencesDemo() {
        val preferences =
            getSharedPreferences("MainActivity", MODE_PRIVATE) //getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("DarkMode", true)
        editor.apply()
    }

    private fun readWriteExternalPublicStoragesDemo() {
        val moviesDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
        Log.i(TAG, "onCreate: Public movies dir: $moviesDir")
        moviesDir.list()?.forEach {
            Log.i(TAG, "onCreate: Public movies contains: $it")
        }
        val publicDocumentsDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        PrintStream(File(publicDocumentsDir, "public.txt")).use {
            it.println("Hello World!")
        }
    }

    private fun externalStorageDemo() {
        val externalStorageState = Environment.getExternalStorageState()
        Log.i(TAG, "onCreate: ExternalStorageState: $externalStorageState")
        if (externalStorageState == Environment.MEDIA_MOUNTED) {
            val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
            Log.i(TAG, "External documents: ${externalFilesDir?.absolutePath}")
        }
    }

    private fun writeToCacheDirDemo() {
        File(cacheDir, "tempfile.txt").bufferedWriter().use {
            it.write("Test data")
        }
    }

    private fun readFroInternalFileDemo(file: File) {
        if (file.exists()) {
            openFileInput("myfile.txt").bufferedReader().useLines { lines ->
                val text = lines.fold("") { prev, next ->
                    "$prev $next"
                }
                Log.i("MainActivity", "myfile.txt: $text")
            }
        }
    }

    private fun writeToInternalFileDemo() {
        openFileOutput("myfile.txt", MODE_PRIVATE).bufferedWriter().use {
            it.write("Hello World")
        }
    }
}