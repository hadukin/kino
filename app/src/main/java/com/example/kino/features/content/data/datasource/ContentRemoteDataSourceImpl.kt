package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.api.MovieClient
import com.example.kino.features.content.data.models.Movie

class ContentRemoteDataSourceImpl(private val api: MovieClient) : ContentRemoteDataSource {
    override suspend fun getMoviePopular(page: Int): ArrayList<Movie> {
        return api.getMoviePopular(page).results
    }
}