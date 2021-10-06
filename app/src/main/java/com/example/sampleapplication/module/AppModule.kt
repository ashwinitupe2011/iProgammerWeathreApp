package com.example.sampleapplication.module

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.example.sampleapplication.BuildConfig
import com.example.sampleapplication.RestApiInterface
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ActivityModule::class])
class AppModule {

    companion object {
         var BASE_URL : String = "https://api.openweathermap.org/data/2.5/"
    }

    @Provides
    fun bindAppContext(application: Application): Context = application


    @Provides
    fun provideContentResolver(context: Context): ContentResolver {
        return context.contentResolver
    }

    @Provides
    fun provideHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }

    @Provides
    @Named("IO")
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named("Main")
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @RequiresApi(Build.VERSION_CODES.N)
    @Provides
    fun provideDefaultLocale(context: Context): Locale {
        return context.resources.configuration.locales.get(0)
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

    @Provides
    fun provideOkHttpClient(context: Context
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val certificatePinnerBuilder = CertificatePinner.Builder()

        try {
            certificatePinnerBuilder.add(BASE_URL)
            builder.certificatePinner(certificatePinnerBuilder.build())
        }catch (e:Exception){e.printStackTrace()}

        return builder.build()
    }

}
