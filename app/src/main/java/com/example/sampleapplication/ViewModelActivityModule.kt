package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapplication.module.ActivityViewModelModule
import dagger.Binds
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module(includes = [ActivityViewModelModule::class])
abstract class ViewModelActivityModule<T : AppCompatActivity> {

    @Binds
    abstract fun bindAppCompatActivity(activity: T): AppCompatActivity

    companion object {

        @Provides
        @JvmStatic
        fun provideViewModelActivity(activity: AppCompatActivity) =
            activity as ViewModelActivity<*>


    }

}