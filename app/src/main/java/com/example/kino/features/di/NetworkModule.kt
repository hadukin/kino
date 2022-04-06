package com.example.kino.features.di

import com.example.kino.BuildConfig
import com.example.kino.features.content.data.api.MovieClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideMovieApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(AppInterceptor())
        .addInterceptor(HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
}

fun provideMovieApi(retrofit: Retrofit): MovieClient = retrofit.create(MovieClient::class.java)

class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // val url = req.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
        val url = req.url.newBuilder().build()
        req = req.newBuilder().url(url).build().newBuilder()
            .addHeader("Authorization", BuildConfig.API_HEADER)
            .build()
        return chain.proceed(req)
    }
}

