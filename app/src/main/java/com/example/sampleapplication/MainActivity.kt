package com.example.sampleapplication

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ViewModelActivity<MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonClick.setOnClickListener { clickMethod() }
    }

    private fun clickMethod() = GlobalScope.launch(Dispatchers.Main)
    {
        val data = viewModel.getApiCall().await()

        Log.d("dxdata","data"+data)

    }
}