package com.example.sampleapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityDetails::class], version = DB_VERSION)
abstract class CityDetailsDataBase : RoomDatabase() {
    abstract fun personDataDao(): CityDataDao

    companion object {
        @Volatile
        private var databseInstance: CityDetailsDataBase? = null

        fun getDatabasenIstance(mContext: Context): CityDetailsDataBase =
            databseInstance ?: synchronized(this) {
                databseInstance ?: buildDatabaseInstance(mContext).also {
                    databseInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, CityDetailsDataBase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }
}

const val DB_VERSION = 1

const val DB_NAME = "PersonDataSample.db"