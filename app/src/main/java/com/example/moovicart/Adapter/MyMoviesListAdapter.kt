package com.example.moovicart.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codequest.util.product
import com.example.moovicart.databinding.MoviesCardBinding


class MyMoviesListAdapter : RecyclerView.Adapter<MyMoviesListAdapter.viewHolder>() {

    var onItemClick: ((product) -> Unit)? = null

    inner class viewHolder(val binding: MoviesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: product) {

            binding.apply {
                mname.text = product.name.toString()
                mrating.text = product.rating.toString()
                mdate.text = product.date.toString()
                Glide.with(itemView).load(product.img)
                    .into(binding.photo)
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<product>() {
        override fun areItemsTheSame(oldItem: product, newItem: product): Boolean {
            return oldItem.img == newItem.img
        }

        override fun areContentsTheSame(oldItem: product, newItem: product): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        return viewHolder(
            MoviesCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val product = differ.currentList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}

