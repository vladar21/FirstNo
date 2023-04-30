package com.example.classwork4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val countries: MutableList<Country> = mutableListOf()
    private val users: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.countriesSpinner)
        val adapter:ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this, R.array.countries,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext, "Selected $position item", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        countries.add(Country("United Kingdom", R.drawable.uk));
        countries.add(Country("Ukraine", R.drawable.ua));
        countries.add(Country("USA", R.drawable.usa));
        countries.add(Country("Ireland", R.drawable.ireland));
        countries.add(Country("United Nations", R.drawable.un));

        val customSpinner: Spinner = findViewById(R.id.customSpinner)
        customSpinner.adapter = CountryAdapter(this, countries)

//        users
        val spinnerUser = findViewById<Spinner>(R.id.usersSpinner)
        val adapterUsers:ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this, R.array.users,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUser.adapter = adapterUsers

        spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext, "Selected $position item", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        users.add(User("Вася Пупкин", R.drawable.user1));
        users.add(User("Иван Иванов", R.drawable.user2));
        users.add(User("Петр Петров", R.drawable.user3));

        val usersSpinner: Spinner = findViewById(R.id.usersSpinner)
        usersSpinner.adapter = UserAdapter(this, users)

    }
}

data class Country(val name: String, val flagId: Int)

class CountryAdapter(context: Context, countries: List<Country>) : ArrayAdapter<Country>(
    context,
    0,
    countries
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView =
            LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false) as TextView
        val country = getItem(position)
        country?.let {
            view.text = country.name
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.contry_item_expand, parent, false)
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