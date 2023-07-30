package com.example.moovicart.Fragments.Producer

import MultiSelectedSpinnerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.moovicart.BaseActivity
import com.example.moovicart.R
import com.example.moovicart.databinding.FragmentSindicateDistributionBinding
import com.example.moovicart.hide

class SindicateDistributionFragment : Fragment() {

    private lateinit var binding: FragmentSindicateDistributionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSindicateDistributionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
        }

        val items = listOf("Syndicate Distribution")
        val items1 = listOf("Select Country","USA","UK","Canada")
        val items2 = listOf("Select Category","Feature Film","Documentary","Short Films")
        val items3 = listOf("Select a Language","Telugu","Hindi","English")
        val items4 = listOf("Select a Certification","A","U","U/A","S","V/U")
        val items5 = listOf("Select Genres","Action","Animation","Drama","Romantic","Thriller","Horror","Sports")
        val items6 = listOf("Select status","Ready for release","Under production","Required Finance","Script Stage")
        val items7 = listOf("Movie Posters","Shooting Pictures")
        val items8 = listOf("Trailer","Event video")

        val spp = view.findViewById<Spinner>(R.id.spinner7)
        val adapt= ArrayAdapter(requireContext(), R.layout.custom_spinner_item, items7)
        adapt.setDropDownViewResource(R.layout.custom_spinner_item)
        spp.adapter = adapt
        spp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
//                Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        val spin = view.findViewById<Spinner>(R.id.spinner8)
        val adaptt= ArrayAdapter(requireContext(), R.layout.custom_spinner_item, items8)
        adaptt.setDropDownViewResource(R.layout.custom_spinner_item)
        spin.adapter = adaptt
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
//                Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        val sp = view.findViewById<Spinner>(R.id.spinner6)
        val adapter0 = ArrayAdapter(requireContext(), R.layout.custom_spinner_item, items6)
        adapter0.setDropDownViewResource(R.layout.custom_spinner_item)
        sp.adapter = adapter0
        sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
//                Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        val multiSelectedSpinner1 = view.findViewById<Spinner>(R.id.spinner1)
        val multiSelectedSpinner2 = view.findViewById<Spinner>(R.id.spinner2)
        val multiSelectedSpinner3 = view.findViewById<Spinner>(R.id.spinner3)
        val multiSelectedSpinner4 = view.findViewById<Spinner>(R.id.spinner4)
        val multiSelectedSpinner5 = view.findViewById<Spinner>(R.id.spinner5)

//        val text = view.findViewById<TextView>(R.id.selectedItemsTextView)
        val adapter = MultiSelectedSpinnerAdapter(requireContext(), items)
        val adapter1 = MultiSelectedSpinnerAdapter(requireContext(), items1)
        val adapter2 = MultiSelectedSpinnerAdapter(requireContext(), items2)
        val adapter3 = MultiSelectedSpinnerAdapter(requireContext(), items3)
        val adapter4 = MultiSelectedSpinnerAdapter(requireContext(), items4)
        val adapter5 = MultiSelectedSpinnerAdapter(requireContext(), items5)

        multiSelectedSpinner1.adapter = adapter1
        multiSelectedSpinner2.adapter = adapter2
        multiSelectedSpinner3.adapter = adapter3
        multiSelectedSpinner4.adapter = adapter4
        multiSelectedSpinner5.adapter = adapter5

//        val button = view.findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//            val selectedItems = adapter.getSelectedItems()
//            val selectedText = selectedItems.joinToString(", ")
//            text.setText("$selectedText")
//            Toast.makeText(requireContext(), "Selected items: $selectedText", Toast.LENGTH_SHORT).show()
//        }
    }

}