package com.example.kino.features.content.data.repository

import android.content.Context
import android.util.Log
import com.example.kino.features.content.data.datasource.ContentLocalDataSource
import com.example.kino.features.content.data.datasource.ContentRemoteDataSource
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository
import kotlinx.coroutines.delay

class ContentRepositoryImpl(
    private val context: Context,
    private val remote: ContentRemoteDataSource,
    private val local: ContentLocalDataSource
) : ContentRepository {
    override suspend fun getMovies(page: Int): List<Movie> {
        // TODO: check internet connection
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

        // delay(1500)
        // throw Exception("Test for no internet case")

        delay(1500)
        val localData = local.getMovies()
        val remoteData = remote.getMovies(page)

        val favoriteListId = localData.filter { it.isFavorite }.map { it.filmId }

        for (item in remoteData) {
            if (favoriteListId.contains(item.filmId)) {
                val id = item.filmId
                remoteData.find { it.filmId == id }?.isFavorite = true
            }
        }

        local.saveAllMovies(remoteData)
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
