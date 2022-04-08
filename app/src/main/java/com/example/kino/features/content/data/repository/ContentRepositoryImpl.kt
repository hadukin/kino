package com.example.kino.features.content.data.repository

import android.content.Context
import android.util.Log
import com.example.kino.features.content.data.datasource.ContentLocalDataSource
import com.example.kino.features.content.data.datasource.ContentRemoteDataSource
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository

class ContentRepositoryImpl(
    private val context: Context,
    private val remote: ContentRemoteDataSource,
    private val local: ContentLocalDataSource
) : ContentRepository {

    override suspend fun getMovies(page: Int): List<Movie>? {
        val all = local.getMovies(0)
        Log.d("GET_ALL_LOCAL_MOVIE", "${all?.size}")
        return remote.getMovies(page)
    }

    override suspend fun saveAllMovies(page: List<Movie>) {
        TODO("Not yet implemented")
    }

    override suspend fun saveToFavorite(item: Movie) {
        local.saveToFavorite(item)
    }

    override suspend fun deleteFromFavorite(item: Movie) {
        local.deleteFromFavorite(item)
    }
}
