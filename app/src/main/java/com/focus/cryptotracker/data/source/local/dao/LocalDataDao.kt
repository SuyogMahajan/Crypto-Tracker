package com.focus.cryptotracker.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.focus.cryptotracker.data.model.Coin
import java.util.*

@Dao
interface LocalDataDao {

    @Insert
    suspend fun addCoin(coin: Coin):Long

    @Delete
    suspend fun deleteCoin(coin:Coin)

    @Query("Select * From coin_table")
    suspend fun getCoins(): List<Coin>

    @Update
    suspend fun updateData(list:List<Coin>)

}