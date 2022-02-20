package com.example.kino.api

import com.example.kino.models.Content
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
}
