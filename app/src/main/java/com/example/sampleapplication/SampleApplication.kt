package com.example.sampleapplication

import android.app.Application
import com.example.sampleapplication.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class SampleApplication : Application() , HasAndroidInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()


        DaggerAppComponent.builder().application(this).build().inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityInjector
    }


}