package com.example.homework72

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.homework72.databinding.FragmentNameDetailBinding
import com.example.homework72.databinding.FragmentNamesListBinding


// MainActivity.kt
class MainActivity : AppCompatActivity() {
    public val names = listOf(
        NameModel("Иван", "01.01.2000", "Описание имени Иван"),
        NameModel("Анна", "02.02.2001", "Описание имени Анна"),
        NameModel("Сергей", "03.03.2002", "Описание имени Сергей")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, NamesListFragment())
                .commit()
        }
    }

    fun getNameModel(name: String): NameModel? {
        return names.find { it.name == name }
    }
}

data class NameModel(val name: String, val date: String, val description: String)

// NamesListFragment.kt
class NamesListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNamesListBinding.inflate(inflater, container, false)

        binding.namesList.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            (activity as MainActivity).names.map { it.name }
        )

        binding.namesList.setOnItemClickListener { _, _, position, _ ->
            val name = (activity as MainActivity).names[position].name
            val detailFragment = NameDetailFragment().apply {
                arguments = bundleOf("name" to name)
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }
}

// NameDetailFragment.kt
class NameDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNameDetailBinding.inflate(inflater, container, false)

        val name = requireArguments().getString("name")!!
        val nameModel = (activity as MainActivity).getNameModel(name)!!

        binding.name.text = nameModel.name
        binding.date.text = nameModel.date
        binding.description.text = nameModel.description

        return binding.root
    }
}
