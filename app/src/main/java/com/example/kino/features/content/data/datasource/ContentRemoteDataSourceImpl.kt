package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.api.MovieClient
import com.example.kino.models.Movie
import com.example.kino.models.MovieDetails
import com.example.kino.models.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.*


class ContentRemoteDataSourceImpl(private val api: MovieClient) : ContentRemoteDataSource {
    override suspend fun getMoviePopular(page: Int, apiKey: String): List<Movie> {
        val result = api.getMoviePopular(page, apiKey)
        return result.body()?.results ?: listOf()
    }

    override suspend fun getMovieDetail(): MovieDetails {
        TODO("Not yet implemented")
    }
}