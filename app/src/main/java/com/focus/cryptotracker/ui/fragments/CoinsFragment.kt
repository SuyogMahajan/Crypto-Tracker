package com.focus.cryptotracker.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.cryptotracker.R
import com.focus.cryptotracker.data.model.Chart
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.source.remote.CryptoApiDataSource
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiEndPoint
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiService
import com.focus.cryptotracker.databinding.FragmentAddCoinsBinding
import com.focus.cryptotracker.databinding.FragmentCoinsBinding
import com.focus.cryptotracker.ui.CLICKED_COIN
import com.focus.cryptotracker.ui.CoinActivity
import com.focus.cryptotracker.ui.adapters.CoinItemAdapter
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoinsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoinsFragment : Fragment() {

    private lateinit var binding: FragmentCoinsBinding
    private lateinit var coinItemAdapter:CoinItemAdapter

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

        val v = listOf("bitcoin", "ethereum", "tether", "shiba-inu")

        var r: List<Coin> = listOf()

        CoroutineScope(Dispatchers.Main).launch {

            val source = CryptoApiDataSource(Dispatchers.IO)
            r = source.getCoin(v)
            coinItemAdapter.updateList(r)

        }

    }


}
