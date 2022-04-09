package com.example.kino.features.content.data.repository

import android.content.Context
import android.util.Log
import com.example.kino.features.content.data.datasource.ContentLocalDataSource
import com.example.kino.features.content.data.datasource.ContentRemoteDataSource
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository
import com.example.kino.utils.NetworkConnection

class ContentRepositoryImpl(
    private val context: Context,
    private val remote: ContentRemoteDataSource,
    private val local: ContentLocalDataSource
) : ContentRepository {
    override suspend fun getMovies(page: Int): List<Movie>? {

        // if (isNetworkAvailable) {
        //     val result = local.getMovies(page)
        //     return result
        // } else {
        //     val result = remote.getMovies(page)
        //     if (result != null) {
        //         local.saveAllMovies(result)
        //     }
        //     return result
        // }

        val result = remote.getMovies(page)
        if (result != null) {
            local.saveAllMovies(result)
        }
        return result
    }

    override suspend fun saveAllMovies(items: List<Movie>) {
        local.saveAllMovies(items)
    }

    override suspend fun saveToFavorite(item: Movie) {
        local.saveToFavorite(item)
    }

    override suspend fun deleteFromFavorite(item: Movie) {
        local.deleteFromFavorite(item)
    }
}
