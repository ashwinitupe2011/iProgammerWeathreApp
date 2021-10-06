package com.example.sampleapplication.module

import com.example.sampleapplication.MainActivity
import com.example.sampleapplication.ViewModelActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    internal abstract fun contributeLoginActivityActivity(): MainActivity
}

@Module
abstract class MainActivityModule : ViewModelActivityModule<MainActivity>()