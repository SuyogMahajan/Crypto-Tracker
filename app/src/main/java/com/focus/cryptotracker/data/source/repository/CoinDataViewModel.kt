package com.focus.cryptotracker.data.source.repository

import android.app.Application
import androidx.lifecycle.*
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.ListSearchCoin
import com.focus.cryptotracker.data.model.SearchCoin
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDataViewModel(application: Application) : AndroidViewModel(application) {

    val repository = CoinRepository(application.baseContext, Dispatchers.IO)

    fun addSearchCoin(coin: SearchCoin) = viewModelScope.launch(Dispatchers.IO) {
        repository.addSearchCoin(coin)
    }

    fun getCoins():LiveData<List<Coin>> {
        val v = MutableLiveData<List<Coin>>()

        viewModelScope.launch (Dispatchers.IO){
            val r = repository.getCoins()
            v.postValue(r)
        }

        return v
    }

    fun getCoinChart(id: String): LiveData<List<List<Double>>>  {

        var v = MutableLiveData<List<List<Double>>>()

        viewModelScope.launch(Dispatchers.IO) {
           val l = repository.getCoinChart(id)
            v.postValue(l)
        }

        return v
           }

    fun getSearch(text: String): LiveData<List<SearchCoin>> {
        var v = MutableLiveData<List<SearchCoin>>()

        viewModelScope.launch {
           val t = repository.getSearch(text).coins
            v.postValue(t)
        }

        return v
    }


    //  delete from list
    fun deleteLocalCoin(coin: Coin) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteLocalCoin(coin)
    }
}