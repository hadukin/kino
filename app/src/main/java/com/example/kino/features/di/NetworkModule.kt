package com.example.kino.features.di

import com.example.kino.features.content.data.api.MovieClient
import okhttp3.OkHttpClient
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
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun provideMovieApi(retrofit: Retrofit): MovieClient = retrofit.create(MovieClient::class.java)

// private fun initRetrofit() {
//     val client = OkHttpClient.Builder().addInterceptor { chain ->
//         val url = chain
//             .request()
//             .url
//             .newBuilder()
//             .build()
//         val response = chain.proceed(
//             chain.request()
//                 .newBuilder()
//                 .addHeader("apikey", API_KEY)
//                 .url(url)
//                 .build()
//         )
//         return@addInterceptor response
//     }
//         .addInterceptor(HttpLoggingInterceptor()
//             .apply {
//                 // if (BuildConfig.DEBUG) {
//                 //     level = HttpLoggingInterceptor.Level.BODY
//                 // }
//             }).build()
//
//     val retrofit = Retrofit.Builder()
//         .baseUrl(BASE_URL)
//         .addConverterFactory(GsonConverterFactory.create())
//         .client(client).build()
//
//     contentApi = retrofit.create(ContentApi::class.java)
//     movieClient = retrofit.create(MovieClient::class.java)
// }