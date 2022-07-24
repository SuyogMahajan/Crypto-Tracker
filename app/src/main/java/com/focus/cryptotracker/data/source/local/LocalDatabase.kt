package com.focus.cryptotracker.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.source.local.dao.LocalDataDao
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Coin::class], version = 1, exportSchema = false)
abstract class LocalDatabase() : RoomDatabase() {
    abstract  fun getDao(): LocalDataDao

    companion object {

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context): LocalDatabase {
            return INSTANCE ?: kotlinx.coroutines.internal.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java, "crypto_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}