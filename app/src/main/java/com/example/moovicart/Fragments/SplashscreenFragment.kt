package com.example.moovicart.Fragments

import android.content.Context
import android.content.Intent
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
import com.example.moovicart.BaseActivity
import com.example.moovicart.R
import com.example.moovicart.hide
import com.example.moovicart.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class SplashscreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                // User is authenticated
                startActivity(Intent(requireContext(), BaseActivity::class.java))
            } else {
                // User is not authenticated
                findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
            }
        }, 3000)

        val view = inflater.inflate(R.layout.fragment_splashscreen, container, false)

        val animTop = AnimationUtils.loadAnimation(view.context, R.anim.from_top)
        val animBottom = AnimationUtils.loadAnimation(view.context, R.anim.from_bottom)

        val tvSplash = view.findViewById<CardView>(R.id.logo)
        val imgSplash = view.findViewById<ImageView>(R.id.name)

        tvSplash.animation = animBottom
        imgSplash.animation = animTop

        return view
    }
}