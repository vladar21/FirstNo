package com.example.homework7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NameDetailFragment : Fragment() {
    private lateinit var nameModel: NameModel
    private lateinit var nameTextView: TextView
    private lateinit var birthdayTextView: TextView
    private lateinit var valueTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_name_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
//        val birthdayTextView = view.findViewById<TextView>(R.id.birthdayTextView)
//        val valueTextView = view.findViewById<TextView>(R.id.valueTextView)

        arguments?.let { bundle ->
            val name = bundle.getString("name")
            name?.let { name ->
                getName(name)?.let { nameModel ->
                    this.nameModel = nameModel
                    bindData()
                }
            }
        }
    }

    private fun getName(name: String): NameModel? {
        return getNames().find { it.name == name }
    }

    private fun getNames(): List<NameModel> {
        return listOf(
            NameModel("John", "01/01/2000", "Value 1"),
            NameModel("Jane", "02/02/2001", "Value 2"),
            NameModel("Bob", "03/03/2002", "Value 3")
        )
    }

    private fun bindData() {
        nameTextView.text = nameModel.name
        birthdayTextView.text = nameModel.birthday
        valueTextView.text = nameModel.value
    }
}