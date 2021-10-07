package com.example.sampleapplication

import android.os.Build.ID
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CityDetails.TABLE_NAME)
data class CityDetails (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var cityId:Int ?= null,
    @ColumnInfo(name = CITY_NAME)
    var cityName: String? = null,
    @ColumnInfo(name = CITY_TEMP)
    var cityTemperature: Double? = null,
    @ColumnInfo(name = CITY_TIME)
    var timeSearched: Int? = null,
)
{
    constructor(cityId: String, cityName: Double, cityTemperature: Int) : this(

    )

    companion object{
        const val TABLE_NAME="personal_details"
        const val ID = "id"
        const val CITY_NAME ="name"
        const val CITY_TEMP = "temperature"
        const val CITY_TIME = "time"
    }

    override fun toString(): String {
        return CITY_NAME.plus(" - ").plus(CITY_TEMP).plus("-").plus(CITY_TIME)
    }
}