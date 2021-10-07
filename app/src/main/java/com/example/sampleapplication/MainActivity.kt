package com.example.sampleapplication

import android.os.Bundle
import android.widget.ArrayAdapter
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

        val cityObjects = arrayOf(CityDetailsDataclass("pune","299","18900")
        )
        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, cityObjects)

        cityNameEdittext.threshold = 1 //start searching for values after typing first character
        cityNameEdittext.setAdapter(adapter)

        infoButtonClick.setOnClickListener {
            cityName = cityNameEdittext.text.toString()
            if(cityName.contains("-")) {
                val cityNameValue = cityName.split("-")
                cityName = cityNameValue[0]
            }
            getWheatherInfo(cityName)
        }
    }

    private fun getWheatherInfo(cityName: String) = GlobalScope.launch(Dispatchers.Main)
    {

        val weatherDetails = viewModel.getWeatherApiCall(cityName).await()

        if(weatherDetails.name!=null)
        textResponse.text = weatherDetails.toString()
        else
            textResponse.text = "No data to display"
        viewModel.saveDataIntoDb(CityDetails(1,weatherDetails.name,
            weatherDetails.main?.temp_min,weatherDetails.timezone))
    }

}