package com.example.kino

import android.app.Application
import com.example.kino.api.ContentApi
import com.example.kino.features.content.data.api.MovieClient
import com.example.kino.features.di.appModule
import com.example.kino.features.di.dataModule
import com.example.kino.features.di.domainModule
import com.example.kino.features.di.networkModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(appModule, networkModule, domainModule, dataModule))
        }
    }

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "6cd5ff50f548e8ae4e99db6d336a460b"

        lateinit var instance: App
            private set
    }
}
// https://api.themoviedb.org/3/movie/508947/credits?api_key=6cd5ff50f548e8ae4e99db6d336a460b
// https://api.themoviedb.org/3/movie/508947?api_key=6cd5ff50f548e8ae4e99db6d336a460b&append_to_response=credits