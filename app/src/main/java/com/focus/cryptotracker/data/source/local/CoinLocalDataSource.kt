package com.focus.cryptotracker.data.source.local

import androidx.lifecycle.LiveData
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.source.local.dao.LocalDataDao

class CoinLocalDataSource(val dao:LocalDataDao) {

    suspend fun getCoins(): List<Coin> {
      return  dao.getCoins()
    }

    suspend fun addCoin(coin: Coin) {
         dao.addCoin(coin)
    }

    suspend fun deleteCoin(coin: Coin) {
        dao.deleteCoin(coin)
    }

    suspend fun updateList( coins:List<Coin>){
        dao.updateData(coins)
    }

}
