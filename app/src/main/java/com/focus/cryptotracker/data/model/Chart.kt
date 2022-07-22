package com.focus.cryptotracker.data.model

import com.google.gson.annotations.SerializedName

data class Chart(

	@SerializedName("total_volumes")
	val totalVolumes: List<List<Double>>? = null,
	@SerializedName("prices")
	val prices: List<List<Double>>? = null,
	@SerializedName("market_caps")
	val marketCaps: List<List<Double>>? = null

)
