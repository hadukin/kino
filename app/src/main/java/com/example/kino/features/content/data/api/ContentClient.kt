package com.example.kino.features.content.data.api

import com.example.kino.models.CastResponse
import com.example.kino.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieClient {
    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
    ): Response<MoviesResponse>

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
    ): Response<CastResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Query("api_key") api_key: String,
    ): Response<CastResponse>
}
