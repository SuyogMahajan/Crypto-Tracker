package com.focus.cryptotracker.data.source.remote

import com.focus.cryptotracker.data.model.Chart
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.ListSearchCoin
import com.focus.cryptotracker.data.model.SearchCoin
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import kotlinx.coroutines.withContext

interface CryptoApiServiceInterface {
    suspend fun getCoin(list:List<String>): List<Coin>

    suspend fun getCoinChart(id:String): List<List<Double>>

    suspend fun getSearch(text:String) : ListSearchCoin
}