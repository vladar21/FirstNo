package com.example.classwork7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val DETAILS = "param1"
private const val ID = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    private var details: String? = null
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            details = it.getString(DETAILS)
            id = it.getString(ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)
        view.findViewById<TextView>(R.id.textViewDetails).text = details
        view.findViewById<TextView>(R.id.textId).text = id
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param details Parameter 1.
         * @return A new instance of fragment DetailsFragment.
         */
        @JvmStatic
        fun newInstance(details: String, id: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(DETAILS, details)
                    putString(ID, id)
                }
            }
    }
}