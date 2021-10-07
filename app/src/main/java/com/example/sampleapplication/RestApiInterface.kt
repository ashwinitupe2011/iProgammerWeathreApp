package com.example.sampleapplication

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestApiInterface {

 /*   @GET("json")
    fun requestWeatherByCityName(
        @Query("address") address: String
    ): Call<ResponseBody>  */

    @GET("data/2.5/weather?")
    fun requestWeatherByCityName(
        @Query("q") lon: String?,
        @Query("appid") app_id: String?
    ): Call<WeatherResponse?>?
}