package com.example.moovicart.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codequest.util.product
import com.example.moovicart.R
import com.example.moovicart.databinding.CardTradingBinding
import com.example.moovicart.util.TradingOptions

class TradingOptionsAdapter(private val movies: List<TradingOptions>) :
    RecyclerView.Adapter<TradingOptionsAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: CardTradingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: TradingOptions) {

            binding.apply {
                type.text = product.name
                Glide.with(itemView).load(product.imageResId)
                    .into(binding.img)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            CardTradingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

}
