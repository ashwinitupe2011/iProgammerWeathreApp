package com.example.sampleapplication

import android.content.ClipData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import android.content.ClipData.Item
import io.reactivex.Maybe


@Dao
interface CityDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCityData(data:CityDetails) : Completable

    @Query("SELECT * FROM ${CityDetails.TABLE_NAME}")
    fun getAllCityRecords(): Maybe<List<CityDetails>>
}