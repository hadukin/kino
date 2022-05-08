package com.example.kino.features.content.data.datasource.content

import com.example.kino.features.content.data.api.MoviesDao
import com.example.kino.features.content.data.datasource.content.ContentLocalDataSource
import com.example.kino.features.content.data.models.Movie


class ContentLocalDataSourceImpl(private val dao: MoviesDao) : ContentLocalDataSource {

    override suspend fun getMovies(): List<Movie> = dao.getAll()

    override suspend fun saveAllMovies(items: List<Movie>) = dao.insertAll(items)

    override suspend fun saveToFavorite(item: Movie) = dao.insert(item)

    override suspend fun deleteFromFavorite(item: Movie) = dao.delete(item)
}