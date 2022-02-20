package com.example.kino.api

import com.example.kino.models.Content
// import com.example.kino.models.Playlist
// import com.example.kino.models.PlaylistResponse
import com.example.kino.models.StationsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
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

    @GET("stations")
    fun getStations(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Call<StationsResponse>
}
