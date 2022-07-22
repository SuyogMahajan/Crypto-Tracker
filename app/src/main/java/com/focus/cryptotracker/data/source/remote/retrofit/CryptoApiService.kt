package com.focus.cryptotracker.data.source.remote.retrofit

import com.focus.cryptotracker.data.model.Chart
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.ListSearchCoin
import com.focus.cryptotracker.data.model.SearchCoin
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApiService {

    @GET("coins/markets")
    suspend fun getCoin(@Query("vs_currency")curr:String = "usd",@Query("ids")list:String): Response<List<Coin>>

    @GET("coins/{id}/ohlc")
    suspend fun getCoinChart(@Path("id") id:String,@Query("vs_currency") curr:String = "usd", @Query("days")days:String):Response<List<List<Double>>>

    @GET("search")
    suspend fun getSearch(@Query("query") q:String): Response<ListSearchCoin>


}
