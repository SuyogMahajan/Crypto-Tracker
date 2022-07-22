package com.focus.cryptotracker.data.source.remote.retrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoApiClient {

    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create()

    private val retrofit = Retrofit.Builder().baseUrl(CryptoApiEndPoint.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val retrofitService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }

}