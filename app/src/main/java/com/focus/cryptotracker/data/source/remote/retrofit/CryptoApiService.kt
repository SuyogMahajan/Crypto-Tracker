package com.focus.cryptotracker.data.source.remote.retrofit

import com.focus.cryptotracker.data.model.Chart
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.CoinList
import retrofit.Response
import retrofit.http.GET
import retrofit.http.Query

interface CryptoApiService {

    @GET("/coins/markets")
    suspend fun getCoin(@Query("vs_currency")curr:String = "usd",@Query("ids")list:List<String>):Response<List<Coin>>

    @GET("/coins/{id}/market_chart")
    suspend fun getCoinChart(@Query("vs_currency")curr:String = "usd",@Query("id") id:String,@Query("days")days:String):Response<Chart>

}