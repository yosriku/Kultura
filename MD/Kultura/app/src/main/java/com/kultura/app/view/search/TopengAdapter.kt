package com.kultura.app.view.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kultura.app.R
import com.kultura.app.data.local.Topeng
import com.kultura.app.view.detail.DetailTopengActivity

class TopengAdapter(private val listTopeng: ArrayList<Topeng>) : RecyclerView.Adapter<TopengAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_nama_topeng)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_deskripsi_topeng)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_topeng, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(name, description) = listTopeng[position]
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailTopengActivity::class.java)
            intentDetail.putExtra(DetailTopengActivity.EXTRA_TOPENG, listTopeng[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listTopeng.size

    
    interface OnItemClickCallback {
        fun onItemClicked(data: Topeng)
    }

}