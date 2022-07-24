package com.focus.cryptotracker.data.source.repository

import android.content.Context
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.ListSearchCoin
import com.focus.cryptotracker.data.model.SearchCoin
import com.focus.cryptotracker.data.source.local.CoinLocalDataSource
import com.focus.cryptotracker.data.source.local.LocalDatabase
import com.focus.cryptotracker.data.source.remote.CoinRemoteDataSource
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CoinRepository(val context: Context,
                     val ioDispatcher: CoroutineDispatcher
                     ) {

    val dao =  LocalDatabase.getDataBase(context).getDao()
    val localDataSource: CoinLocalDataSource = CoinLocalDataSource(dao)
    val coinRemoteDataSource: CoinRemoteDataSource = CoinRemoteDataSource(ioDispatcher)

       // get Live data
       suspend fun getCoins():List<Coin> = withContext(ioDispatcher){

           val v = localDataSource.getCoins()

           val l = v.map { it.id }
           val res = coinRemoteDataSource.getCoin(l)

           if(res.isSuccessful){
                 return@withContext res.body()!!
           }else{
                 return@withContext v
           }
       }

       // adding to list
          // fetch serch coin id serch for that id and add coin to local database
        suspend fun addSearchCoin(coin:SearchCoin) = withContext(ioDispatcher){

            val id = coin.id
            val dataCoin = coinRemoteDataSource.getCoin(listOf(id)).body()!!
            localDataSource.addCoin(dataCoin[0])

        }

    suspend fun getSearch(text: String): ListSearchCoin = withContext(ioDispatcher){
        val r = coinRemoteDataSource.getSearch(text)
      return@withContext  if(r.isSuccessful){
          r.body()!!
      }else{
          ListSearchCoin(listOf())
        }
    }

      suspend fun getCoinChart(id: String): List<List<Double>> {
        val r = coinRemoteDataSource.getCoinChart(id)
          if(r.isSuccessful){
              return r.body()!!
          }else{
              return listOf(listOf())
          }
    }

       //  delete from list
       suspend fun deleteLocalCoin(coin: Coin) = withContext(ioDispatcher){
           localDataSource.deleteCoin(coin)
       }

}