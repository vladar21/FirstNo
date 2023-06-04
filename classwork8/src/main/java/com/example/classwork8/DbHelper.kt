package com.example.classwork8

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context?) : SQLiteOpenHelper(context, "todo", null, 2) {

    companion object {
        const val TAG = "DbHelper"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i(TAG, "onCreate: create database")
        db?.execSQL(
            """
            create table todolist (
                _id integer primary key autoincrement,
                task text not null,
                description text,
                priority integer default(1)
            )
        """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i(TAG, "onUpgrade: upgrade database from $oldVersion to $newVersion")
        if (oldVersion == 1 && newVersion == 2) {
            // add new column
            db?.execSQL("""
                alter table todolist 
                    add column priority integer default(1)
            """.trimIndent())
        }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i(TAG, "onDowngrade: downgrade database from $oldVersion to $newVersion")
        if(oldVersion == 2 && newVersion == 1) {
            // drop column
        }
    }
}