package com.focus.cryptotracker.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.source.remote.CoinRemoteDataSource
import com.focus.cryptotracker.data.source.repository.CoinDataViewModel
import com.focus.cryptotracker.databinding.FragmentCoinsBinding
import com.focus.cryptotracker.ui.CLICKED_COIN
import com.focus.cryptotracker.ui.CoinActivity
import com.focus.cryptotracker.ui.adapters.CoinItemAdapter
import kotlinx.coroutines.*


class CoinsFragment : Fragment() {

    private lateinit var binding: FragmentCoinsBinding
    private lateinit var coinItemAdapter:CoinItemAdapter
    private lateinit var viewModel: CoinDataViewModel

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentCoinsBinding.inflate(inflater)
        coinItemAdapter = CoinItemAdapter(requireContext())

        val onRvItemClick = object :CoinItemAdapter.onItemClickInterface{
            override fun onItemClick(coin: Coin) {

                val i = Intent(requireContext(),CoinActivity::class.java);
                i.putExtra(CLICKED_COIN,coin)
                startActivity(i)
            }
        }

        coinItemAdapter.setOnItemClickInterface(onRvItemClick)
        binding.rvCoins.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCoins.adapter = coinItemAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CoinDataViewModel::class.java)

        refreshData()
        binding.CoinRefreshLayout.setOnRefreshListener {
            refreshData()
        }

    }

    private fun refreshData(){
        viewModel.getCoins().observe(requireActivity(),object:Observer<List<Coin>>{
            override fun onChanged(t: List<Coin>?) {
                coinItemAdapter.updateList(t!!)
                binding.CoinRefreshLayout.isRefreshing = false
            }
        })
    }

}
