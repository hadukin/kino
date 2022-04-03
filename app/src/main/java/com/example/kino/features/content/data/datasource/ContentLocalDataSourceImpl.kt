package com.example.kino.features.content.data.datasource

import com.example.kino.models.Movie
import com.example.kino.models.MovieDetails

class ContentLocalDataSourceImpl : ContentLocalDataSource {
    override fun save(content: List<Movie>) {
        TODO("Not yet implemented")
    }

    override suspend fun getMoviePopular(page: Int, apiKey: String): List<Movie>? {
        TODO("Not yet implemented")
    }
}