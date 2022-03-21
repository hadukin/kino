package com.example.kino.api

import com.example.kino.models.CastResponse
import com.example.kino.models.Content
import com.example.kino.models.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentApi {
    @GET("content")
    fun getContent(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Call<List<Content>>

    // @GET("playlists")
    // fun getPlaylists(
    //     @Query("limit") limit: Int,
    //     @Query("offset") offset: Int,
    // ): Call<PlaylistResponse>

    // @GET("stations")
    // fun getStations(
    //     @Query("limit") limit: Int,
    //     @Query("offset") offset: Int,
    // ): Call<StationsResponse>

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
    ): Call<MoviesResponse>

    @GET("movie/{id}/credits")
    fun getMovieCredits(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
    ): Call<CastResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Query("api_key") api_key: String,
    ): Call<CastResponse>

}
