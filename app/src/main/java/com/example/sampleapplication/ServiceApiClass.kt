package com.example.sampleapplication

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ServiceApiClass @Inject constructor(private val restApiInterface: RestApiInterface) {
    fun getweatherApiCall(cityName: String): WeatherResponse? {

        val result = restApiInterface.requestWeatherByCityName(cityName,"094aa776d64c50d5b9e9043edd4ffd00")
        val apiResponse = result?.execute()
        if(apiResponse!!.isSuccessful)
        {
            return apiResponse.body() as WeatherResponse
        }
        else
        return null
    }
}