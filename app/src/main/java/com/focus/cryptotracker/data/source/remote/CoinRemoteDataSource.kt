package com.focus.cryptotracker.data.source.remote
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.model.ListSearchCoin
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import com.focus.cryptotracker.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoinRemoteDataSource(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :
    CryptoApiServiceInterface {

    override suspend fun getCoin(list: List<String>): Result<List<Coin>> = withContext(ioDispatcher) {


        return@withContext try{

            val s = list.joinToString(",")
           val r =  CryptoApiClient.retrofitService.getCoin("usd", s)

           if(r.isSuccessful){
               Result.Success(r.body()!!)
           }else{
               Result.Success(null)
           }
        }catch (exception :Exception){
               Result.Error(exception)
    }

    }

    override suspend fun getCoinChart(id: String): Result<List<List<Double>>> = withContext(ioDispatcher) {
        return@withContext  try{
            val r =
                CryptoApiClient.retrofitService.getCoinChart(id,"usd", "7")
            if(r.isSuccessful){
            Result.Success(r.body()!!)
        }else{
            Result.Success(null)
        }
    }catch (exception :Exception){
        Result.Error(exception)
    }

    }

    override suspend fun getSearch(text: String): Result<ListSearchCoin> = withContext(ioDispatcher){
        return@withContext try{
            val r =
                CryptoApiClient.retrofitService.getSearch(text)
            if(r.isSuccessful){
                Result.Success(r.body()!!)
            }else{
                Result.Success(null)
            }
        }catch (exception :Exception){
            Result.Error(exception)
        }
    }

}