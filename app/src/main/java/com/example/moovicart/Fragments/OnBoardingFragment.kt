package com.example.moovicart.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.demoapp.OnboardingPages.FirstPage
import com.example.demoapp.OnboardingPages.FourtPage
import com.example.demoapp.OnboardingPages.SecondPage
import com.example.demoapp.OnboardingPages.ThirdPage
import com.example.moovicart.Adapter.ViewPagerAdapter
import com.example.moovicart.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_on_boarding, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstPage(),
            SecondPage(),
            ThirdPage(),
            FourtPage()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = adapter

        val indicator = view.findViewById<DotsIndicator>(R.id.dots_indicator)
        indicator.attachTo(viewPager)

        return view
    }


}