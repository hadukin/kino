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

    var isConnected = false

    init {
        isNetworkConnected()
    }

    private fun isNetworkConnected() {
        NetworkConnection(context) {
            isConnected = it
        }
    }

    override suspend fun getMovies(page: Int): List<Movie> {
        // delay(1500)

        val localData = local.getMovies()

        // throw Exception("ERROR")


        Log.d("isConnected", "${localData.size}")

        if (isConnected) {
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
        } else {
            return localData
        }


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
