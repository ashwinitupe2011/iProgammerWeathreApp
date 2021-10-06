package com.example.sampleapplication

import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ServiceApiClass @Inject constructor(private val restApiInterface: RestApiInterface) {
    fun getApiCall(): Any {

        val result = restApiInterface.requestCityAddressByName("hhhh")
        val result1 = result.execute()
        if(result1.isSuccessful)
        {
            return "Success"
        }
        else
        return "false"
    }
}