package com.example.sampleapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CityDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersonData(data:cityDetails) : Completable

    @Query("SELECT * FROM ${cityDetails.TABLE_NAME}")
    fun getAllRecords():Single<List<cityDetails>>
}