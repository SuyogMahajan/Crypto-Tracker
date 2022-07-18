package com.focus.cryptotracker.data.model

data class Coin(
	val priceChangePercentage24h: Double? = null,
	val symbol: String? = null,
	val priceChange24h: Double? = null,
	val marketCapRank: Int? = null,
	val id: String? = null,
	val image: String? = null,
	val circulatingSupply: Int? = null,
	val totalSupply: Int? = null,
	val name: String? = null,
	val currentPrice: Int? = null
)

data class CoinList(val list:List<Coin>? = null)
