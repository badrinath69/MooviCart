package com.example.moovicart.Fragments.Producer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecom.util.Resource
import com.example.moovicart.Adapter.MyMoviesListAdapter
import com.example.moovicart.R
import com.example.moovicart.Viewmodels.MyMoviesListViewModel
import com.example.moovicart.databinding.FragmentMyMovieListBinding
import kotlinx.coroutines.flow.collectLatest

class MyMovieListFragment : Fragment() {

    private lateinit var spa: MyMoviesListAdapter
    private lateinit var binding: FragmentMyMovieListBinding
    private val viewmodel by viewModels<MyMoviesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyMovieListBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SetupBestProductRV()

        lifecycleScope.launchWhenStarted {
            viewmodel.c.collectLatest {
                when(it) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        spa.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Resource.Error -> {
                        hideLoading()

                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                    else ->Unit

                }
            }
        }
    }

    private fun hideLoading() {
        binding.pb5.visibility=View.INVISIBLE
    }
    private fun showLoading() {
        binding.pb5.visibility=View.VISIBLE
    }
    private fun SetupBestProductRV() {

        spa= MyMoviesListAdapter()
        binding.furniturerv.apply {
            layoutManager= GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
            val  adapter=spa
            binding.furniturerv.adapter=adapter
        }
    }

}