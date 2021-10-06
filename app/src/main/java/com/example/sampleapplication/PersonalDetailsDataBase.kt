package com.example.sampleapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonData::class], version = DB_VERSION)
abstract class PersonalDetailsDataBase : RoomDatabase() {
    abstract fun personDataDao(): PersonalDataDao

    companion object {
        @Volatile
        private var databseInstance: PersonalDetailsDataBase? = null

        fun getDatabasenIstance(mContext: Context): PersonalDetailsDataBase =
            databseInstance ?: synchronized(this) {
                databseInstance ?: buildDatabaseInstance(mContext).also {
                    databseInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, PersonalDetailsDataBase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }
}

const val DB_VERSION = 1

const val DB_NAME = "PersonDataSample.db"