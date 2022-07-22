package com.focus.cryptotracker.data.source.remote

import com.focus.cryptotracker.data.model.Chart
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.ListSearchCoin
import com.focus.cryptotracker.data.model.SearchCoin
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import com.focus.cryptotracker.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class CryptoApiDataSource(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :
    CryptoApiServiceInterface {

    override suspend fun getCoin(list: List<String>): List<Coin> = withContext(ioDispatcher) {
        val s = list.joinToString(",")
        return@withContext CryptoApiClient.retrofitService.getCoin("usd", s).body()!!

    }

    override suspend fun getCoinChart(id: String): List<List<Double>> = withContext(ioDispatcher) {
        return@withContext CryptoApiClient.retrofitService.getCoinChart(id,"usd", "7").body()!!
    }

    override suspend fun getSearch(text: String): ListSearchCoin = withContext(ioDispatcher){
        return@withContext CryptoApiClient.retrofitService.getSearch(text).body()!!
    }

}
