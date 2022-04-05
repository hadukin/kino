package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.api.MovieClient
import com.example.kino.models.Movie

class ContentRemoteDataSourceImpl(private val api: MovieClient) : ContentRemoteDataSource {
    override suspend fun getMoviePopular(page: Int, apiKey: String): ArrayList<Movie> {
        return api.getMoviePopular(page, apiKey).results
    }
}