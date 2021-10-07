package com.example.sampleapplication

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ServiceApiClass @Inject constructor(private val restApiInterface: RestApiInterface) {
    fun getweatherApiCall(): WeatherResponse? {

        val result = restApiInterface.requestWeatherByCityName("pune","094aa776d64c50d5b9e9043edd4ffd00")
        val result1 = result?.execute()
        if(result1!!.isSuccessful)
        {
            return result1.body()
        }
        else
        return null
    }
}