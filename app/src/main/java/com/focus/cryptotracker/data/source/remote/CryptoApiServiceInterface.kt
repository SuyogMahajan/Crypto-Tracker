package com.focus.cryptotracker.data.source.remote

import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.ListSearchCoin
import retrofit2.Response

interface CryptoApiServiceInterface {
    suspend fun getCoin(list:List<String>):Response< List<Coin>>

    suspend fun getCoinChart(id:String): Response<List<List<Double>>>

    suspend fun getSearch(text:String) : Response<ListSearchCoin>
}
