package com.example.homework81

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var listsDatabaseHelper: Dbhomework8DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listsDatabaseHelper = Dbhomework8DatabaseHelper(this)

        seedDB()

        val linearLayout = findViewById<LinearLayout>(R.id.linear_layout)

//        val addProductButton = Button(this)
        val addButton = findViewById<Button>(R.id.add)
        addButton.text = "Add Product"
        addButton.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }

//        linearLayout.addView(addProductButton)
    }

    private fun seedDB() {
        val dbHelper = Dbhomework8DatabaseHelper(this)
        val db = dbHelper.readableDatabase

        val query = "SELECT EXISTS(SELECT 1 FROM Lists WHERE name = ?)"
        val selectionArgs = arrayOf("List 1")

        val cursor = db.rawQuery(query, selectionArgs)

        if (cursor.moveToFirst()) {
            val exists = cursor.getInt(0) == 1

            if (!exists) {

                val valuesList = listOf(
                    ContentValues().apply {
                        put("name", "List 1")
                        put("date", "1449462120")
                        put("description", "This is list 1")
                    },
                    ContentValues().apply {
                        put("name", "List 2")
                        put("date", "1449472233")
                    },
                    ContentValues().apply {
                        put("name", "List 3")
                        put("date", "1449483585")
                    }
                )

                for (values in valuesList) {
                    db.insert("Lists", null, values)
                }
            }
        }

        cursor.close()

        val query2 = "SELECT EXISTS(SELECT 1 FROM Types WHERE label = ?)"
        val selectionArgs2 = arrayOf("List 1")

        val cursor2 = db.rawQuery(query2, selectionArgs2)

        if (cursor2.moveToFirst()) {
            val exists2 = cursor2.getInt(0) == 1

            if (!exists2) {

                val valuesList2 = listOf(
                    ContentValues().apply {
                        put("label", "шт")
                        put("rule", "int")
                    },
                    ContentValues().apply {
                        put("label", "кг")
                        put("rule", "float")
                    },
                    ContentValues().apply {
                        put("label", "л")
                        put("rule", "float")
                    }
                )

                for (values in valuesList2) {
                    db.insert("Types", null, values)
                }
            }
        }

        cursor2.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        listsDatabaseHelper.close()
    }

    // 2. Определение методов для создания, получения, удаления и изменения Lists, Products и Types:
    class Dbhomework8DatabaseHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_TABLE_LISTS)
            db?.execSQL(CREATE_TABLE_TYPES)
            db?.execSQL(CREATE_TABLE_PRODUCTS)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_LISTS")
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_TYPES")
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
            onCreate(db)
        }

        companion object {
            private const val DATABASE_NAME = "dbhomework8"
            private const val DATABASE_VERSION = 1

            private const val TABLE_LISTS = "Lists"
            private const val TABLE_TYPES = "Types"
            private const val TABLE_PRODUCTS = "Products"

            private const val CREATE_TABLE_LISTS =
                "CREATE TABLE $TABLE_LISTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, date INTEGER NOT NULL, description TEXT);"

            private const val CREATE_TABLE_TYPES =
                "CREATE TABLE $TABLE_TYPES (_id INTEGER PRIMARY KEY AUTOINCREMENT, label TEXT NOT NULL, rule TEXT NOT NULL);"

            private const val CREATE_TABLE_PRODUCTS =
                "CREATE TABLE $TABLE_PRODUCTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, count REAL NOT NULL, list_id INTEGER NOT NULL, checked INTEGER NOT NULL, count_type INTEGER NOT NULL);"
        }
    }
}

// 1. Создание таблиц Lists, Types и Products:
class Lists {
    var id: Int = 0
    var name: String? = null
    var date: Int = 0
    var description: String? = null
}

class Types {
    var id: Int = 0
    var label: String? = null
    var rule: String? = null
}

class Products {
    var id: Int = 0
    var name: String? = null
    var count: Double = 0.0
    var list_id: Int = 0
    var checked: Int = 0
    var count_type: Int = 0
}



// 3. add product
class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        // Wire up the "Save" button to the event handler
        val saveButton = findViewById<Button>(R.id.add_product_button)
        saveButton.setOnClickListener {

            val dbHelper = MainActivity.Dbhomework8DatabaseHelper(this)
            val db = dbHelper.writableDatabase

            // Get the product name and price from the UI
            val productName = findViewById<EditText>(R.id.product_name).text.toString()
            val productCount = findViewById<EditText>(R.id.product_count).text.toString().toDouble()
            val productListId = findViewById<EditText>(R.id.list_id).text.toString().toDouble()

            val checkbox = findViewById<CheckBox>(R.id.checked)
            val productChecked = checkbox.text.toString()

            val productCountType = findViewById<EditText>(R.id.count_type_id).text.toString().toDouble()

            // Save the new product to the database
            val values = ContentValues().apply {
                put("name", productName)
                put("count", productCount)
                put("list_id", productListId)
                put("checked", productChecked)
                put("count_type", productCountType)
            }

            db.insert("Products", null, values)

            // Return to the main activity
            finish()
        }
    }

    private fun getTypesAll(db: SQLiteDatabase?) : Array<String> {
        // Query the database for the values you want to populate the array with
        val cursor = db?.query(
            "types",
            arrayOf("name"),
            null,
            null,
            null,
            null,
            null
        )

        // Create an empty ArrayList to hold the values
        val typesList = ArrayList<String>()

        // Loop through the cursor and add each value to the ArrayList
        while (cursor?.moveToNext()!!) {
            val type = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            typesList.add(type)
        }

        // Convert the ArrayList to an Array and use it to create an ArrayAdapter for your spinner
        val typesArray = typesList.toTypedArray()
        return typesArray

    }


}





