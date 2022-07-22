package com.focus.cryptotracker.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.cryptotracker.R
import com.focus.cryptotracker.data.source.remote.CryptoApiDataSource
import com.focus.cryptotracker.databinding.FragmentAddCoinsBinding
import com.focus.cryptotracker.databinding.FragmentCoinsBinding
import com.focus.cryptotracker.ui.adapters.CoinItemAdapter
import com.focus.cryptotracker.ui.adapters.SearchCoinItemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddCoinsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCoinsFragment : Fragment() {

    private lateinit var remoteSource: CryptoApiDataSource
    private lateinit var binding: FragmentAddCoinsBinding
    private lateinit var searchCoinItemAdapter: SearchCoinItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentAddCoinsBinding.inflate(layoutInflater)
         searchCoinItemAdapter = SearchCoinItemAdapter(requireContext())

         remoteSource = CryptoApiDataSource(Dispatchers.IO)

         binding.rvSearchCoin.layoutManager = LinearLayoutManager(requireContext())
         binding.rvSearchCoin.adapter = searchCoinItemAdapter

         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {

            var s = binding.searchCoin.text.toString()

            if(s.isBlank()) s = ""

            CoroutineScope(Dispatchers.Main).launch {

             searchCoinItemAdapter.updateList(remoteSource.getSearch(s).coins)

            }
        }
    }

}