package com.focus.cryptotracker.data.source.remote

import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.ListSearchCoin
import com.focus.cryptotracker.util.Result

interface CryptoApiServiceInterface {
    suspend fun getCoin(list:List<String>):Result< List<Coin>>

    suspend fun getCoinChart(id:String): Result<List<List<Double>>>

    suspend fun getSearch(text:String) : Result<ListSearchCoin>
}
