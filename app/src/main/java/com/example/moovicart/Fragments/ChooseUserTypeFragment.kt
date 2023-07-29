package com.example.moovicart.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moovicart.R
import com.example.moovicart.databinding.FragmentChooseUserTypeBinding

class ChooseUserTypeFragment : Fragment() {

    private lateinit var binding: FragmentChooseUserTypeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentChooseUserTypeBinding.inflate(layoutInflater)
        binding.audience.setOnClickListener {
            findNavController().navigate(R.id.action_chooseUserTypeFragment_to_signUpFragment)
        }
        return binding.root
    }

}