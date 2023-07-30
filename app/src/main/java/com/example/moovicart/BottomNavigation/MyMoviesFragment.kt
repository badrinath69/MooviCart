package com.example.moovicart.BottomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.moovicart.Adapter.CustomPagerAdapter
import com.example.moovicart.Fragments.Producer.MyMovieListFragment
import com.example.moovicart.Fragments.Producer.TradingOptionsFragment
import com.example.moovicart.R
import com.example.moovicart.databinding.FragmentMyMovieListBinding
import com.google.android.material.tabs.TabLayout

class MyMoviesFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_my_movies, container, false)
        viewPager = rootView.findViewById(R.id.viewPager)
        tabLayout = rootView.findViewById(R.id.tabLayout)

        // Create your list of fragments and titles
        val fragmentList = listOf(TradingOptionsFragment(), MyMovieListFragment())
        val titleList = listOf("Trading Options", "My Movies List")

        // Create the adapter using the custom FragmentPagerAdapter
        val adapter = CustomPagerAdapter(childFragmentManager, fragmentList, titleList)

        // Set the adapter to the ViewPager
        viewPager.adapter = adapter

        // Connect the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager)

        return rootView
    }
    override fun onResume() {
        super.onResume()
    }
}