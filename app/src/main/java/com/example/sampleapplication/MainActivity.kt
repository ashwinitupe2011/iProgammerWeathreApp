package com.example.sampleapplication

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ViewModelActivity<MainViewModel>() {
    var cityName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataBaseInstance = CityDetailsDataBase.getDatabasenIstance(this)
        viewModel.setInstanceOfDb(dataBaseInstance)

        observerViewModel()

        infoButtonClick.setOnClickListener {
            cityName = cityNameEdittext.text.toString()
            getWheatherInfo()
        }
    }

    private fun getWheatherInfo() = GlobalScope.launch(Dispatchers.Main)
    {

        val weatherDetails = viewModel.getWeatherApiCall(cityName).await()
    }

    private fun observerViewModel() {
        viewModel?.cityList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                Log.d("AAAAAA",it.toString())
            } else {
                Log.d("AAAAAA","it.toString()")
            }
        })
    }
}