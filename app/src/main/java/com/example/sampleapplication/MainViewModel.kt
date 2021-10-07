package com.example.sampleapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val apiService : ServiceApiClass
) : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    private var dataBaseInstance: CityDetailsDataBase ?= null

    var cityList = MutableLiveData<List<CityDetails>>()

    fun setInstanceOfDb(dataBaseInstance: CityDetailsDataBase) {
        this.dataBaseInstance = dataBaseInstance
    }

    fun getWeatherApiCall(cityName: String): Deferred<Any> = GlobalScope.async(Dispatchers.Default)  {
       val data = apiService.getweatherApiCall(cityName)
        return@async data!!
    }

    fun saveDataIntoDb(data: CityDetails){

        dataBaseInstance?.cityDataDao()?.insertPersonData(data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
            },{

            })?.let {
                compositeDisposable.add(it)
            }
    }

    fun getCityData(){

        dataBaseInstance?.cityDataDao()?.getAllRecords()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                if(!it.isNullOrEmpty()){
                    cityList.postValue(it)
                }else{
                    cityList.postValue(listOf())
                }
            },{
            })?.let {
                compositeDisposable.add(it)
            }
    }
}