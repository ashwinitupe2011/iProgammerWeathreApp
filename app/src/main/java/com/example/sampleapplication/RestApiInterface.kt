package com.example.sampleapplication

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestApiInterface {

    @POST("/hjgjhg")
    fun getData(
        @Body aaa : String
    ): Call<ResponseBody>

    @GET("json")
    fun requestCityAddressByName(
        @Query("address") address: String
    ): Call<ResponseBody>
}