package com.example.kino.features.content.data.datasource

import android.util.Log
import com.example.kino.features.content.data.api.MoviesDao
import com.example.kino.features.content.data.models.Movie


class ContentLocalDataSourceImpl(private val dao: MoviesDao) : ContentLocalDataSource {

    override suspend fun getMovies(page: Int): List<Movie> = dao.getAll(page)

    override suspend fun saveAllMovies(items: List<Movie>) = dao.insertAll(items)

    override suspend fun saveToFavorite(item: Movie) = dao.insert(item)

    override suspend fun deleteFromFavorite(item: Movie) = dao.delete(item)
}