package com.focus.cryptotracker.data.model

import com.google.gson.annotations.SerializedName

data class SearchCoin(
                      @SerializedName("symbol")
                      val symbol: String,
                      @SerializedName("market_cap_rank")
                      val marketCapRank: Double,
                      @SerializedName("id")
                      val id: String,
                      @SerializedName("image")
                      val image: String,
                      @SerializedName("name")
                      val name: String
                      )

data class ListSearchCoin(

    @SerializedName("coins")
    val coins:List<SearchCoin>

    )
