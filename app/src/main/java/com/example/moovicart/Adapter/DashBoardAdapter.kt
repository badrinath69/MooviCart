package com.example.moovicart.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moovicart.databinding.DbMovieCardBinding
import com.example.moovicart.util.DashBoardDc
class DashBoardAdapter(listner: onItemClickListner)  : RecyclerView.Adapter<DashBoardAdapter.DbViewHolder>() {
    private var mListener : onItemClickListner?=null
    interface onItemClickListner : OnItemClickListener {
        fun onItemClick(position: Int)
    }
    init{
        this.mListener=listner
    }
    inner class DbViewHolder(val binding: DbMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DashBoardDc, listner: onItemClickListner) {
            binding.apply {
                dbitemText.text = data.mname
                Glide.with(itemView).load(data.mimg)
                    .into(binding.dbitemPhoto)
                frvItem1.setOnClickListener {
                    listner.onItemClick(adapterPosition)
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<DashBoardDc>() {
        override fun areItemsTheSame(oldItem: DashBoardDc, newItem: DashBoardDc): Boolean {
            return oldItem.mimg == newItem.mimg
        }

        override fun areContentsTheSame(oldItem: DashBoardDc, newItem: DashBoardDc): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DbViewHolder {
        return DbViewHolder(
            DbMovieCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    override fun onBindViewHolder(holder: DbViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product, mListener!!)
    }
}













