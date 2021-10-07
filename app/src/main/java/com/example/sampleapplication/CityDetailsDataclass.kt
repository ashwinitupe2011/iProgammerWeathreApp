package com.example.sampleapplication

data class CityDetailsDataclass (
    val cityName : String,
    val cityTemp : String,
    val time : String
){
    override fun toString(): String {
        return cityName.plus(" - ").plus(cityTemp).plus(" - ").plus(time)
    }
}