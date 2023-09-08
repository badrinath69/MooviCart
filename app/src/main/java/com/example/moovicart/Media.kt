package com.example.moovicart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecom.util.Resource
import com.example.moovicart.Adapter.OverViewAdapter
import com.example.moovicart.Adapter.OverViewPicAdapter
import com.example.moovicart.Viewmodels.OverViewViewModel
import com.example.moovicart.databinding.FragmentMediaBinding
import com.example.moovicart.databinding.FragmentOverviewBinding
import kotlinx.coroutines.flow.collectLatest

class Media : Fragment() {
    private lateinit var fm1a: OverViewPicAdapter

    private lateinit var binding: FragmentMediaBinding
    private val viewmodel by viewModels<OverViewViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentMediaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOverViewRv()
        lifecycleScope.launchWhenStarted {
            viewmodel.c.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
//                        fm1a.differ.submitList(it.data)
//                        binding.foTv.text = it?.data?.get(0)!!.ov_des
                        fm1a.setData(it.data?.get(0)!!.ov_images)
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

    private fun setupOverViewRv(){
        fm1a = OverViewPicAdapter()
//        fov2a = OverViewPicAdapter()
        binding.fmRv1.apply {
            layoutManager= LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            val  adapter=fm1a
            binding.fmRv1.adapter=adapter

        }
        binding.fmRv2.apply {
            layoutManager= LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            val  adapter=fm1a
            binding.fmRv2.adapter=adapter

        }
    }
    private fun hideLoading() {
        binding.fmPb.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding.fmPb.visibility = View.VISIBLE
    }

}