package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.api.MoviesDao
import com.example.kino.features.content.data.models.Movie


class ContentLocalDataSourceImpl(private val dao: MoviesDao) : ContentLocalDataSource {
    override suspend fun getMoviePopular(page: Int): ArrayList<Movie>? {
        TODO("Not yet implemented")
        // return dao.getAll()
    }

    override suspend fun saveToFavorite(item: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavorite(item: Movie) {
        TODO("Not yet implemented")
    }

}