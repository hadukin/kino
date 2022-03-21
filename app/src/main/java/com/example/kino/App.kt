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
                    // if (BuildConfig.DEBUG) {
                    //     level = HttpLoggingInterceptor.Level.BODY
                    // }
                }).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()

        contentApi = retrofit.create(ContentApi::class.java)
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