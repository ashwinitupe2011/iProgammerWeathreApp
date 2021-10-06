package com.example.sampleapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PersonalDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersonData(data:PersonData) : Completable

    @Query("SELECT * FROM ${PersonData.TABLE_NAME}")
    fun getAllRecords():Single<List<PersonData>>
}