package com.example.sampleapplication.module

import android.app.Application
import android.content.Context
import com.example.sampleapplication.RestApiInterface
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [ActivityModule::class])
class AppModule {

    companion object {
        lateinit var BASE_URL : String
    }

    @Provides
    fun bindAppContext(application: Application): Context = application


    @Provides
    fun updateBackendUrl(url : String): String {
        BASE_URL = url
        return url
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setPrettyPrinting()
            .setVersion(1.0)
            .create()
    }

    @Provides
    fun provideRequestService(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): RestApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        return retrofit.create(RestApiInterface::class.java)
    }

}
