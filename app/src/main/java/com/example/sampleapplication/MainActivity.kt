package com.example.sampleapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ViewModelActivity<MainViewModel>() {
    var cityName = ""
    var cityList : List<CityDetails>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataBaseInstance = CityDetailsDataBase.getDatabasenIstance(this)
        viewModel.setInstanceOfDb(dataBaseInstance)

        val movies = arrayOf("Avengers: Endgame", "Captain Marvel", "Shazam!", "Spider-Man: Far From Home", "Dark Phoenix", "Hellboy", "Glass", "Reign of the Superman", "Brightburn")
        val adapter1 = ArrayAdapter(this, android.R.layout.select_dialog_item, movies)

        cityNameEdittext.threshold = 1 //start searching for values after typing first character
        cityNameEdittext.setAdapter(adapter1)


 /*       observerViewModel()*/

        infoButtonClick.setOnClickListener {
            cityName = cityNameEdittext.text.toString()
            getWheatherInfo()
        }
    }

    private fun getWheatherInfo() = GlobalScope.launch(Dispatchers.Main)
    {

        val weatherDetails = viewModel.getWeatherApiCall(cityName).await()
    }

    /*private fun observerViewModel() {
        viewModel?.cityList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
              *//* cityList = viewModel.getCityData()*//*
            } else {

            }
        })
    }*/
}