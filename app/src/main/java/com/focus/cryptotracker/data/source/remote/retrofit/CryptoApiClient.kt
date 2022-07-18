package com.focus.cryptotracker.data.source.remote.retrofit

import retrofit.GsonConverterFactory
import retrofit.Retrofit

object CryptoApiClient {

    private val retrofit = Retrofit.Builder().baseUrl(CryptoApiEndPoint.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }

}