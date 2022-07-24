package com.focus.cryptotracker.data.source.remote
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.ListSearchCoin
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoinRemoteDataSource(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :
    CryptoApiServiceInterface {

    override suspend fun getCoin(list: List<String>): Response<List<Coin>> = withContext(ioDispatcher) {
        val s = list.joinToString(",")
        return@withContext CryptoApiClient.retrofitService.getCoin("usd", s)
    }

    override suspend fun getCoinChart(id: String): Response<List<List<Double>>> = withContext(ioDispatcher) {
        return@withContext CryptoApiClient.retrofitService.getCoinChart(id,"usd", "7")
    }

    override suspend fun getSearch(text: String): Response<ListSearchCoin> = withContext(ioDispatcher){
        return@withContext CryptoApiClient.retrofitService.getSearch(text)
    }

}