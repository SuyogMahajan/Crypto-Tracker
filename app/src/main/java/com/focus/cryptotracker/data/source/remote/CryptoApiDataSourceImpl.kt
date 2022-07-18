package com.focus.cryptotracker.data.source.remote

import com.focus.cryptotracker.data.model.Chart
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiClient
import com.focus.cryptotracker.data.source.remote.retrofit.CryptoApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import  com.focus.cryptotracker.util.Result

class CryptoApiDataSourceImpl(private val ioDispatcher: CoroutineDispatcher,
                              private val retrofitClient:CryptoApiService = CryptoApiClient.retrofitService
                              ) :CryptoApiDataSource {

    override suspend fun getCoin(curr: String, list: List<String>): Result<List<Coin>> =
        withContext(ioDispatcher) {
          return@withContext try{
             val r = retrofitClient.getCoin(curr,list)

              if(r.isSuccess){
                  Result.Success(r.body())
              }else{
                  Result.Success(null)
              }

          }catch (exception:Exception){
              Result.Error(Exception())
          }

    }

    override suspend fun getCoinChart(curr: String, id: String, days: String): Result<Chart> = withContext(ioDispatcher) {
            return@withContext try{
                val r = retrofitClient.getCoinChart(curr,id,days)

                if(r.isSuccess){
                    Result.Success(r.body())
                }else{
                    Result.Success(null)
                }

            }catch (exception:Exception){
                Result.Error(Exception())
            }
            }

    }