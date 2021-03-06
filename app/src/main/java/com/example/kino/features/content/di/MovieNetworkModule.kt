package com.example.kino.features.content.di

import com.example.kino.BuildConfig
import com.example.kino.features.content.data.api.MovieApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val movieNetworkModule = module {
    factory { provideOkHttpClient() }
    factory { provideMovieApi(get()) }
    single { provideRetrofit(get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(AppInterceptor())
        .addInterceptor(HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
}

private fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

private class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // val url = req.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
        val url = req.url.newBuilder().build()
        req = req.newBuilder().url(url).build().newBuilder()
            .addHeader("X-API-KEY", BuildConfig.API_KEY)
            .build()
        return chain.proceed(req)
    }
}

