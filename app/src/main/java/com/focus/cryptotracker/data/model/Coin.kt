package com.focus.cryptotracker.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Coin(
	@SerializedName("price_change_percentage_24h")
	val priceChangePercentage24h: Double,
	@SerializedName("symbol")
	val symbol: String,
	@SerializedName("price_change_24h")
	val priceChange24h: Double,
	@SerializedName("market_cap_rank")
	val marketCapRank: Long,
	@SerializedName("market_cap_change_percentage_24h")
	val marketCapChangePercentage24h: Double,
	@SerializedName("id")
	val id: String,
	@SerializedName("image")
	val image: String,
	@SerializedName("circulating_supply")
	val circulatingSupply: Double,
	@SerializedName("total_supply")
	val totalSupply: Double,
	@SerializedName("name")
	val name: String,
	@SerializedName("current_price")
	val currentPrice: Double
):Serializable
