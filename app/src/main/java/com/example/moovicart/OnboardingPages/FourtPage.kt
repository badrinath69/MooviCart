package com.example.demoapp.OnboardingPages

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.marginBottom
import androidx.navigation.fragment.findNavController
import com.example.moovicart.R


class FourtPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_onboard4, container, false)
        val next = view.findViewById<Button>(R.id.login)

        next.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_SendOTPFragment)
            onBoardingIsFinished()
        }
        return view
    }

    private fun onBoardingIsFinished(){

        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("finished",false)
//        editor.apply()
    }


}