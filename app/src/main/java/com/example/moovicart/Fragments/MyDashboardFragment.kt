package com.example.moovicart.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecom.util.Resource
import com.example.moovicart.Adapter.DashBoardAdapter
import com.example.moovicart.R
import com.example.moovicart.Viewmodels.MyDashBoardViewModel
import com.example.moovicart.databinding.FragmentMyDashboardBinding
import com.example.moovicart.util.Communicator
import kotlinx.coroutines.flow.collectLatest

class MyDashboardFragment : Fragment() {
    private lateinit var fdba: DashBoardAdapter
    private lateinit var binding: FragmentMyDashboardBinding
    private val viewmodel by viewModels<MyDashBoardViewModel>()
    private lateinit var communicator:Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyDashboardBinding.inflate(layoutInflater)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SetupBestProductRV()
        communicator = activity as Communicator
        lifecycleScope.launchWhenStarted {



            viewmodel.c.collectLatest {
                when(it) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        fdba.differ.submitList(it.data)
                        binding.fmdLikes.text=viewmodel.likes.toString()
                        binding.fmdShares.text=viewmodel.shares.toString()
                        binding.fmdRatings.text=viewmodel.rating.toString()
                        binding.fmdRedeems.text=viewmodel.redeem.toString()

                        Log.d("like","like = ${viewmodel.likes} " +
                                "share = ${viewmodel.shares} rating = ${viewmodel.rating}  redeem = ${viewmodel.redeem}")

                        Log.d("yyy","size = ${it.data?.size} ")
                        Log.d("yyyy","${it.data}")
                        val k = it.data
                        var si = k?.size
                        var p =0
                        val i = k?.indices
                        if (i != null) {
                            for (j in i){
                                Log.d("qqq","catogery = ${k?.get(j)?.mname}")

                            }
                        }
                        while (si!! > 0){

                            Log.d("yyyyy"," catogery = ${k?.get(p)?.mname}")
                            si-=1
                            p+=1
                        }

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
        binding.fdbPb.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding.fdbPb.visibility = View.VISIBLE
    }
    private fun SetupBestProductRV() {

        fdba= DashBoardAdapter(object :DashBoardAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {
//                communicator.passPosition(position)
                val bundle = bundleOf("posi" to position.toString())
//                val bundle2 = bundleOf("moviename" to )
                findNavController().navigate(R.id.action_myDashboardFragment_to_movieDetailsFragment,bundle)
//                Toast.makeText(requireContext(),"clicked $position",Toast.LENGTH_SHORT).show()

//                requireActivity().supportFragmentManager.beginTransaction().apply {
//                    replace(R.id.info_frame, MovieDetailsFragment())
//                    commit()
//                }
            }
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

        })
        binding.fdbRv.apply {
            layoutManager= GridLayoutManager(requireContext(),3, GridLayoutManager.VERTICAL,false)
            val  adapter=fdba
            binding.fdbRv.adapter=adapter

        }
    }
}