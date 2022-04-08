package com.example.kino.features.content.data.datasource

import android.util.Log
import com.example.kino.features.content.data.api.MoviesDao
import com.example.kino.features.content.data.models.Movie


class ContentLocalDataSourceImpl(private val dao: MoviesDao) : ContentLocalDataSource {
    override suspend fun getMovies(page: Int): List<Movie> {
        return dao.getAll(page)
    }

    override suspend fun saveAllMovies(items: List<Movie>) {
        return dao.insertAll(items)
    }

    override suspend fun saveToFavorite(item: Movie) {
        // Log.d("SAVE_MOVIE", "${item}")
        // val allBefore = dao.getAll()
        // Log.d("BEFORE_SAVE", "${allBefore.size}")
        dao.insert(item)
        // val allAfter = dao.getAll()
        // Log.d("AFTER_SAVE", "${allAfter.size}")
    }

    override suspend fun deleteFromFavorite(item: Movie) {
        // Log.d("DELETE_MOVIE", "${item}")
        // val allBefore = dao.getAll()
        // Log.d("BEFORE_DELETE", "${allBefore.size}")
        dao.delete(item)
        // val allAfter = dao.getAll()
        // Log.d("AFTER_DELETE", "${allAfter.size}")
    }
}