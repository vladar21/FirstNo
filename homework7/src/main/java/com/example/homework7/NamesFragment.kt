package com.example.homework7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class NamesFragment : Fragment() {
    private lateinit var namesAdapter: NamesAdapter
    private lateinit var namesRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_names, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        namesRecyclerView = view.findViewById(R.id.fragmentContainerView)
        namesAdapter = NamesAdapter(getNames(), ::onNameClicked)
        namesRecyclerView.adapter = namesAdapter
        namesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        namesAdapter.notifyDataSetChanged()


    }

    private fun getNames(): List<NameModel> {
        return listOf(
            NameModel("John", "01/01/2000", "Value 1"),
            NameModel("Jane", "02/02/2001", "Value 2"),
            NameModel("Bob", "03/03/2002", "Value 3")
        )
    }

    private fun onNameClicked(name: NameModel) {
        val bundle = bundleOf("name" to name.name)
        val fragment = NameDetailFragment()
        fragment.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}