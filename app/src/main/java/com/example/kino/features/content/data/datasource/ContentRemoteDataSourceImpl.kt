package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.api.MovieClient
import com.example.kino.features.content.data.models.Movie

class ContentRemoteDataSourceImpl(private val api: MovieClient) : ContentRemoteDataSource {
    override suspend fun getMovies(page: Int): List<Movie> {
        return api.getMoviePopular(page).results
    }

    override suspend fun saveAllMovies(page: List<Movie>) {
        TODO("Not yet implemented")
    }

    override suspend fun saveToFavorite(item: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavorite(item: Movie) {
        TODO("Not yet implemented")
    }
}