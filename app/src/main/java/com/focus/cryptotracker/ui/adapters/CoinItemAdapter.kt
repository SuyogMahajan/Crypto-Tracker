package com.focus.cryptotracker.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.focus.cryptotracker.R
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.databinding.ListItemCoinDetailsBinding

class CoinItemAdapter(val context: Context):RecyclerView.Adapter<CoinItemAdapter.getViewHolder>() {

    interface onItemClickInterface{
        fun onItemClick(coin:Coin)
    }


   private lateinit var onItemClick:onItemClickInterface

    fun setOnItemClickInterface(click:onItemClickInterface){
        onItemClick = click
    }

   private var list:ArrayList<Coin> =  ArrayList<Coin>()

    inner class getViewHolder(val binding: ListItemCoinDetailsBinding,val onItemClicked:onItemClickInterface):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.nameTv.text = list[position].name
            binding.prizeTv.text = "$" + list[position].currentPrice.toString()
            binding.varTv.text = "%"  +list[position].priceChangePercentage24h.toString()
            binding.totalSupplyTv.text = "$" +list[position].totalSupply.toLong().toString()
            Glide.with(context).load(list[position].image).placeholder(R.drawable.ic_bitcoin).into(binding.iconIV)

            binding.root.setOnClickListener {
                onItemClick.onItemClick(list[position])
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {
        return getViewHolder(ListItemCoinDetailsBinding.inflate(LayoutInflater.from(context),parent,false),onItemClick)
    }

    override fun onBindViewHolder(holder: getViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Coin>){
        if(updatedList.isNullOrEmpty()){
            list.clear()
        }else{
            list = updatedList as ArrayList<Coin>
        }

        notifyDataSetChanged()
    }

}