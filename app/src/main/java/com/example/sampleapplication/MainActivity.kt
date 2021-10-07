package com.example.sampleapplication

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ViewModelActivity<MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataBaseInstance = PersonalDetailsDataBase.getDatabasenIstance(this)
        viewModel.setInstanceOfDb(dataBaseInstance)

        observerViewModel()

        infoButtonClick.setOnClickListener { getWheatherInfo() }
    }

    private fun getWheatherInfo() = GlobalScope.launch(Dispatchers.Main)
    {

        val weatherDetails = viewModel.getWeatherApiCall().await()

    }

    private fun observerViewModel() {
        viewModel?.personsList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                Log.d("AAAAAA",it.toString())
            } else {
                Log.d("AAAAAA","it.toString()")
            }
        })
    }
}