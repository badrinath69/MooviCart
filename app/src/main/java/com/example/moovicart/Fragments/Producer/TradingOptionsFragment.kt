package com.example.moovicart.Fragments.Producer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moovicart.R
import com.example.moovicart.databinding.FragmentTradingOptionsBinding

class TradingOptionsFragment : Fragment() {

    private lateinit var binding: FragmentTradingOptionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTradingOptionsBinding.inflate(layoutInflater)

        binding.syndicateDistribution.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.add(R.id.lay, SindicateDistributionFragment())
            transaction.commit()
        }

        return binding.root
    }

}