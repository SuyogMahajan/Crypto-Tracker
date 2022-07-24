package com.focus.cryptotracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "coin_table")
data class Coin(

	@PrimaryKey(autoGenerate = true)
	val lid:Long = 0L,


	val price_change_percentage_24h: Double,
	val symbol: String,
	val price_change_24h: Double,
	val market_cap_rank: Long,

	val market_cap_change_percentage_24h: Double,

	val id: String,

	val image: String,
	val circulating_supply: Double,

	val total_supply: Double,
	val name: String,

	val current_price: Double
):Serializable
