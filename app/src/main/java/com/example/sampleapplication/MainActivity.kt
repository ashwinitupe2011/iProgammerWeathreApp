package com.example.sampleapplication

import android.os.Bundle
import android.widget.ArrayAdapter
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

        val cityList = viewModel.getCityData()

        val dataBaseInstance = CityDetailsDataBase.getDatabasenIstance(this)
        viewModel.setInstanceOfDb(dataBaseInstance)

        val movieObjects = arrayOf(cityList)
        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, movieObjects)

        cityNameEdittext.threshold = 1 //start searching for values after typing first character
        cityNameEdittext.setAdapter(adapter)

        infoButtonClick.setOnClickListener {
            cityName = cityNameEdittext.text.toString()
            getWheatherInfo()
        }
    }

    private fun getWheatherInfo() = GlobalScope.launch(Dispatchers.Main)
    {

        val weatherDetails = viewModel.getWeatherApiCall(cityName).await()

        textResponse.text = weatherDetails.toString()
        viewModel.saveDataIntoDb(CityDetails(weatherDetails.name, weatherDetails.main.temp_min ,weatherDetails.timezone))
    }

}