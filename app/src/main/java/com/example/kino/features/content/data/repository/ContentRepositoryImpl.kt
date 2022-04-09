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

        val localData = local.getMovies()
        val remoteData = remote.getMovies(page)?.toMutableList()

        val favoriteListId = localData.filter { it.isFavorite }.map { it.filmId }

        if (remoteData != null) {
            for (item in remoteData) {
                if (favoriteListId.contains(item.filmId) == true) {
                    val id = item.filmId
                    remoteData.find { it.filmId == id }?.isFavorite = true
                }
            }
        }

        if (remoteData != null) {
            local.saveAllMovies(remoteData)
        }
        return remoteData
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
