package com.example.sampleapplication

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ServiceApiClass @Inject constructor(private val restApiInterface: RestApiInterface) {
    fun getweatherApiCall(cityName: String): WeatherResponse? {

        val result =
            restApiInterface.requestWeatherByCityName(cityName, "094aa776d64c50d5b9e9043edd4ffd00")
        val apiResponse = result?.execute()
        if (apiResponse!!.isSuccessful) {
            return apiResponse.body() as WeatherResponse
        } else
            return WeatherResponse(
                Coord(null, null),
                emptyList(),
                null,
                Main(null, null, null, null, null, null, null, null),
                null,Wind(null,null,null),Clouds(null),null,Sys(null,null,null),null,null,null,null
            )
    }
}