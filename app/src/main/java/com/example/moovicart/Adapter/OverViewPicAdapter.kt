package com.example.moovicart.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moovicart.R
import com.example.moovicart.databinding.FoCardBinding
import com.example.moovicart.databinding.FoCardVBinding

class OverViewPicAdapter:RecyclerView.Adapter<OverViewPicAdapter.Ov1ViewHolder>() {

    inner class Ov1ViewHolder(val binding:FoCardBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(s:String){
            Glide.with(itemView).load(s)
                .into(binding.foRvitem)
        }



    }

    var dataList = emptyList<String>()
//    var dataList2 = emptyList<String>()
    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(data:List<String>){
        this.dataList=data
        notifyDataSetChanged()
    }
//    @SuppressLint("NotifyDataSetChanged")
//    internal fun setData2(data:List<String>){
//        this.dataList2=data
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Ov1ViewHolder {
        return Ov1ViewHolder(

            FoCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Ov1ViewHolder, position: Int) {
//        val data = dataList[position]
//            holder.bind(data)
    }
}