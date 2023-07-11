package com.example.moovicart.Fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import com.example.moovicart.R


class SplashscreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
// Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
            if (onFinished()) {
                findNavController().navigate(R.id.navigate_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
            }
        }, 3000)

        val view = inflater.inflate(R.layout.fragment_splashscreen, container, false)

        val animTop = AnimationUtils.loadAnimation(view.context, R.anim.from_top)
        val animBottom = AnimationUtils.loadAnimation(view.context, R.anim.from_bottom)

        val tvSplash = view.findViewById<CardView>(R.id.logo)
        val imgSplash = view.findViewById<TextView>(R.id.name)

        tvSplash.animation = animBottom
        imgSplash.animation = animTop

        return view
    }

    private fun onFinished(): Boolean {
        val sharedPreferences =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("finished", false)
    }

}