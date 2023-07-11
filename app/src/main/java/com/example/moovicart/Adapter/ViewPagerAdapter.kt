package com.example.moovicart.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(
    list: ArrayList<Fragment>,
    fragmentManager: FragmentManager,
    lyfecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lyfecycle) {
    private val fragments = list


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}