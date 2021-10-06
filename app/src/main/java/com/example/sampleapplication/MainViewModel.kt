package com.example.sampleapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val apiService : ServiceApiClass
) : ViewModel() {
    fun getApiCall(): Deferred<Any> = GlobalScope.async(Dispatchers.Default)  {
       val data = apiService.getApiCall()
        return@async data
    }
}