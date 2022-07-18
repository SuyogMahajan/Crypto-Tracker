package com.focus.cryptotracker.data.model

data class Chart(

	val totalVolumes: List<List<Long>>? = null,
	val prices: List<List<Long>>? = null,
	val marketCaps: List<List<Long>>? = null

)

