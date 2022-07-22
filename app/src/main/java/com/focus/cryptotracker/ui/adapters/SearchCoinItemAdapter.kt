package com.focus.cryptotracker.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.focus.cryptotracker.data.model.SearchCoin
import com.focus.cryptotracker.databinding.ListItemCoinDetailsBinding

class SearchCoinItemAdapter (val context: Context): RecyclerView.Adapter<SearchCoinItemAdapter.getViewHolder>() {

    var list: ArrayList<SearchCoin> = ArrayList<SearchCoin>()

    inner class getViewHolder(val binding: ListItemCoinDetailsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {
        return getViewHolder(
            ListItemCoinDetailsBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchCoinItemAdapter.getViewHolder, position: Int) {

        holder.binding.nameTv.text = list[position].name

        holder.binding.varTv.visibility = View.INVISIBLE
        holder.binding.prizeTv.visibility = View.INVISIBLE
        holder.binding.totalSupplyTv.visibility = View.INVISIBLE

        holder.binding.addButton.visibility = View.VISIBLE

        Glide.with(context).load(list[position].image).into(holder.binding.iconIV)

    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<SearchCoin>) {
        if (updatedList.isNullOrEmpty()) {
            list.clear()
        } else {
            list = updatedList as ArrayList<SearchCoin>
        }
        notifyDataSetChanged()
    }

}