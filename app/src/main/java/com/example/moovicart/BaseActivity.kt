package com.example.moovicart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import com.example.moovicart.BottomNavigation.BookingFragment
import com.example.moovicart.BottomNavigation.MyMoviesFragment
import com.example.moovicart.BottomNavigation.ProfileFragment
import com.example.moovicart.BottomNavigation.ReviewsFragment
import com.example.moovicart.Fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.isAppearanceLightNavigationBars = true
        windowInsetsController?.isAppearanceLightStatusBars = true

        setContentView(R.layout.activity_base)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.homeFragment2 -> {
                    supportFragmentManager.beginTransaction().addToBackStack("")
                        .add(R.id.main, HomeFragment()).commit()
                    true
                }

                R.id.myMoviesFragment -> {
                    supportFragmentManager.beginTransaction().addToBackStack("")
                        .add(R.id.main, MyMoviesFragment()).commit()
                    true
                }

                R.id.bookingFragment -> {
                    supportFragmentManager.beginTransaction().addToBackStack("")
                        .add(R.id.main, BookingFragment()).commit()
                    true
                }

                R.id.reviewsFragment -> {
                    supportFragmentManager.beginTransaction().addToBackStack("")
                        .add(R.id.main, ReviewsFragment()).commit()
                    true
                }
                R.id.profileFragment -> {
                    supportFragmentManager.beginTransaction().addToBackStack("")
                        .add(R.id.main, ProfileFragment()).commit()
                    true
                }

                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()

        supportFragmentManager.beginTransaction()
            .add(R.id.main, HomeFragment()).commit()

    }
}