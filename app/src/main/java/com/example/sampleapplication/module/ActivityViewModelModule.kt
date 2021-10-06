package com.example.sampleapplication.module

import androidx.lifecycle.ViewModel
import com.example.sampleapplication.MainViewModel
import com.example.sampleapplication.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract  class ActivityViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindLoginViewModel(viewModel: MainViewModel): ViewModel
}