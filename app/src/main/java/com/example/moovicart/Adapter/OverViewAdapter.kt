package com.example.moovicart.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moovicart.databinding.DbMovieCardBinding
import com.example.moovicart.databinding.FoCardBinding
import com.example.moovicart.databinding.FragmentOverviewBinding
import com.example.moovicart.util.DashBoardDc
import com.example.moovicart.util.OverViewDc

class OverViewAdapter:RecyclerView.Adapter<OverViewAdapter.OvViewHolder>(){
    inner class OvViewHolder(val binding:FragmentOverviewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: OverViewDc) {
            binding.apply {
                foTv.text = data.ov_des
            }
        }
    }

    private val diffCallBack = object :DiffUtil.ItemCallback<OverViewDc>(){
        override fun areItemsTheSame(oldItem: OverViewDc, newItem: OverViewDc): Boolean {
            return oldItem.ov_des == newItem.ov_des
        }

        override fun areContentsTheSame(oldItem: OverViewDc, newItem: OverViewDc): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,diffCallBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OvViewHolder {
        return OvViewHolder(
            FragmentOverviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: OvViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }
}