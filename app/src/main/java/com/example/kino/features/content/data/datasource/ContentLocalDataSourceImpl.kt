package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.models.Movie
import com.example.kino.models.MovieDetails

class ContentLocalDataSourceImpl : ContentLocalDataSource {
    override fun saveToFavorite(content: List<Movie>) {
        TODO("Not yet implemented")
    }

    override suspend fun getMoviePopular(page: Int): ArrayList<Movie>? {
        TODO("Not yet implemented")
    }
}