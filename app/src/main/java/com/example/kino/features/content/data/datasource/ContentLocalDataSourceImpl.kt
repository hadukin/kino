package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.api.MoviesDao
import com.example.kino.features.content.data.models.Movie


class ContentLocalDataSourceImpl(private val dao: MoviesDao) : ContentLocalDataSource {
    override suspend fun getMoviePopular(page: Int): List<Movie>? {
        return dao.getAll()
    }

    override suspend fun saveToFavorite(item: Movie) {
        dao.insert(item)
    }

    override suspend fun deleteFromFavorite(item: Movie) {
        dao.delete(item)
    }

}