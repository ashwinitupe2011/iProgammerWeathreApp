package com.example.sampleapplication

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

    private var dataBaseInstance: PersonalDetailsDataBase ?= null

    var personsList = MutableLiveData<List<PersonData>>()

    fun setInstanceOfDb(dataBaseInstance: PersonalDetailsDataBase) {
        this.dataBaseInstance = dataBaseInstance
    }

    fun getApiCall(): Deferred<Any> = GlobalScope.async(Dispatchers.Default)  {
       val data = apiService.getApiCall()
        return@async data
    }

    fun saveDataIntoDb(data: PersonData){

        dataBaseInstance?.personDataDao()?.insertPersonData(data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
            },{

            })?.let {
                compositeDisposable.add(it)
            }
    }
}