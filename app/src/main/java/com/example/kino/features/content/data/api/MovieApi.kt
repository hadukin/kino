package com.example.kino.features.content.data.api

import com.example.kino.features.content.data.models.CastResponse
import com.example.kino.features.content.data.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("top?type=TOP_250_BEST_FILMS")
    suspend fun getMoviePopular(
        @Query("page") page: Int,
    ): MoviesResponse

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(
        @Path("id") id: Int,
    ): CastResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(): CastResponse
}
