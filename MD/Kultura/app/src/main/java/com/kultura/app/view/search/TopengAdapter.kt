package com.kultura.app.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kultura.app.data.response.TopengItem
import com.kultura.app.databinding.ItemTopengBinding


class TopengAdapter : ListAdapter<TopengItem, TopengAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTopengBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

    }

    class MyViewHolder(private val binding: ItemTopengBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: TopengItem) {
            binding.tvNamaTopeng.text = user.name
            Glide.with(binding.root).load(user.imageUrl).into(binding.ivItemPhoto)
            binding.tvDeskripsiTopeng.text = user.informasi
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TopengItem>(){
            override fun areItemsTheSame(oldItem: TopengItem, newItem: TopengItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TopengItem, newItem: TopengItem): Boolean {
                return oldItem == newItem
            }
        }
    }



}