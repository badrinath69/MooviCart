package com.example.moovicart.Fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moovicart.Adapter.TradingOptionsAdapter
import com.example.moovicart.Fragments.Producer.SindicateDistributionFragment
import com.example.moovicart.R
import com.example.moovicart.databinding.FragmentHomeBinding
import com.example.moovicart.util.TradingOptions
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val moviesList = listOf(
        TradingOptions(R.drawable.syndicate_distributing, "Syndicate Distribution"),
        TradingOptions(R.drawable.outright_fixed_price, "Outright Fixed Price"),
        TradingOptions(R.drawable.movie_marketing, "Movie Marketing"),
        TradingOptions(R.drawable.movie_release, "Movie Release")
        // Add more movies here
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moviesAdapter = TradingOptionsAdapter(moviesList)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesAdapter
        }

    }
}