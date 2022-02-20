package com.example.kino

import android.app.Application
import com.example.kino.api.ContentApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var contentApi: ContentApi

    override fun onCreate() {
        super.onCreate()

        instance = this
        initRetrofit()
    }

    private fun initRetrofit() {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val url = chain
                .request()
                .url
                .newBuilder()
                .build()
            val response = chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("apikey", API_KEY)
                    .url(url)
                    .build()
            )
            return@addInterceptor response
        }
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                }).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()

        contentApi = retrofit.create(ContentApi::class.java)
    }

    companion object {

        const val BASE_URL = "https://api.napster.com/v2.2/"
        const val API_KEY = "Njc0NWI0MzEtZWQyOS00ZGY2LTgzYjQtM2FmOTYxNWUyMzNk"

        lateinit var instance: App
            private set
    }
}