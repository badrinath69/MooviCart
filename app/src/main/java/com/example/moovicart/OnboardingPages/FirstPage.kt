package com.example.demoapp.OnboardingPages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.moovicart.R


class FirstPage : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_onboard1, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)
        val next = view.findViewById<Button>(R.id.next)

        next.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view

    }


}