package com.example.sampleapplication

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

    var cityList : ArrayList<CityDetailsDataclass>? = null
    protected val compositeDisposable = CompositeDisposable()

    private var dataBaseInstance: CityDetailsDataBase ?= null


    fun setInstanceOfDb(dataBaseInstance: CityDetailsDataBase) {
        this.dataBaseInstance = dataBaseInstance
    }

    fun getWeatherApiCall(cityName: String): Deferred<WeatherResponse> = GlobalScope.async(Dispatchers.Default)  {
       val data = apiService.getweatherApiCall(cityName)
        return@async data!!
    }

    fun saveDataIntoDb(data: CityDetails){

        dataBaseInstance?.cityDataDao()?.insertCityData(data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
            },{

            })?.let {
                compositeDisposable.add(it)
            }
    }

    fun getCityData(){

        dataBaseInstance?.cityDataDao()?.getAllCityRecords()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                if(!it.isNullOrEmpty()){

                }else{
                }
                it?.forEach {
                    CityDetailsDataclass(it.cityName.toString(), it.cityTemperature.toString(),
                        it.timeSearched.toString()
                    )
                }
            },{
            })?.let {
                compositeDisposable.add(it)
            }

    }
}