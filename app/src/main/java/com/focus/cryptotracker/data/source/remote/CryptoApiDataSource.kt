package com.focus.cryptotracker.data.source.remote

import com.focus.cryptotracker.data.model.Chart
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.CoinList
import retrofit.Response
import retrofit.http.GET
import retrofit.http.Query
import com.focus.cryptotracker.util.Result

interface CryptoApiDataSource {

    suspend fun getCoin(curr:String = "usd", list:List<String>): Result<List<Coin>>

    suspend fun getCoinChart(curr:String = "usd", id:String, days:String): Result<Chart>

}