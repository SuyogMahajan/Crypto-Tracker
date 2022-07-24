package com.focus.cryptotracker.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.cryptotracker.data.model.SearchCoin
import com.focus.cryptotracker.data.source.remote.CoinRemoteDataSource
import com.focus.cryptotracker.data.source.repository.CoinDataViewModel
import com.focus.cryptotracker.databinding.FragmentAddCoinsBinding
import com.focus.cryptotracker.ui.adapters.SearchCoinItemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddCoinsFragment : Fragment() {

    private lateinit var remoteSource: CoinRemoteDataSource
    private lateinit var binding: FragmentAddCoinsBinding
    private lateinit var searchCoinItemAdapter: SearchCoinItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


         binding = FragmentAddCoinsBinding.inflate(layoutInflater)
         searchCoinItemAdapter = SearchCoinItemAdapter(requireContext())

         binding.rvSearchCoin.layoutManager = LinearLayoutManager(requireContext())
         binding.rvSearchCoin.adapter = searchCoinItemAdapter

         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      val viewModel = ViewModelProvider(requireActivity()).get(CoinDataViewModel::class.java)

        binding.searchButton.setOnClickListener {

            var s = binding.searchCoin.text.toString()

            if(s.isBlank()) s = ""

            val v = viewModel.getSearch(binding.searchCoin.text.toString())

            v.observe(requireActivity(),object :Observer<List<SearchCoin>>{
                override fun onChanged(t: List<SearchCoin>?) {
                    searchCoinItemAdapter.updateList(t!!)
                    binding.searchCoin.text.clear()
                }
            })


        }

        val onClick = object : SearchCoinItemAdapter.onButtonClickInterface{
            override fun onAddButtonClick(searchCoin: SearchCoin) {
                viewModel.addSearchCoin(searchCoin)
                    Toast.makeText(requireContext(),"Added Successfully.",Toast.LENGTH_SHORT).show()

            }
        }

        searchCoinItemAdapter.setOnButtonClickInterface(onClick)
    }
}
