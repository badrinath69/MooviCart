package com.example.moovicart.Fragments

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecom.util.Resource
import com.example.moovicart.Adapter.OverViewAdapter
import com.example.moovicart.Adapter.OverViewPicAdapter
import com.example.moovicart.R
import com.example.moovicart.Viewmodels.OverViewViewModel
import com.example.moovicart.databinding.FragmentOverviewBinding
import com.example.moovicart.util.Communicator
import kotlinx.coroutines.flow.collectLatest

class OverViewFragment : Fragment() {

    private lateinit var fova:OverViewAdapter
    private lateinit var fov1a:OverViewPicAdapter
//    private lateinit var fov2a:OverViewPicAdapter

    private lateinit var binding: FragmentOverviewBinding
    private val viewmodel by viewModels<OverViewViewModel>()
    private var pos:Int?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOverviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOverViewRv()

        val k =arguments?.getString("posi")?.toInt()
//        Toast.makeText(requireContext(), "positon = ${k}", Toast.LENGTH_SHORT).show()

        lifecycleScope.launchWhenStarted {
            viewmodel.c.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        fova.differ.submitList(it.data)
                        binding.foTv.text = it?.data?.get(0)!!.ov_des
                        fov1a.setData(it.data?.get(0)!!.ov_images)
//                        fov2a.setData2(it.data.get(0).ov_vid)
//                        binding.foTv.text = it.data?.get(0)?.ov_des

                        Log.d("www","${it.data.get(0).ov_vid}")
//                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                        hideLoading()


                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                        hideLoading()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupOverViewRv() {
        fova = OverViewAdapter()
        fov1a = OverViewPicAdapter()
//        fov2a = OverViewPicAdapter()
        binding.foRv1.apply {
            layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            val  adapter=fov1a
            binding.foRv1.adapter=adapter

        }
        binding.foRv2.apply {
            layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            val  adapter=fov1a
            binding.foRv2.adapter=adapter
        }
    }

    private fun hideLoading() {
        binding.fovPb.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding.fovPb.visibility = View.VISIBLE
    }




}