package com.example.kino.features.content.data.repository

import android.content.Context
import android.util.Log
import com.example.kino.features.content.data.api.MoviesDao
import com.example.kino.features.content.data.datasource.ContentRemoteDataSource
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository

class ContentRepositoryImpl(
    private val context: Context,
    private val remote: ContentRemoteDataSource,
    private val local: MoviesDao
) : ContentRepository {

    override suspend fun getMoviePopular(page: Int): ArrayList<Movie>? {
        val all = local.getAll()
        Log.d("GET_ALL_LOCAL_MOVIE", "${all.size}")
        return remote.getMoviePopular(page)
    }

    override suspend fun saveToFavorite(item: Movie) {
        Log.d("INSERT_MOVIE", "${item}")
        local.insert(item)
        val all = local.getAll()
        Log.d("AFTER_INSERT", "${all.size}")
    }

    override suspend fun deleteFromFavorite(item: Movie) {
        Log.d("DELETE_MOVIE", "${item}")
        local.delete(item)
        val all = local.getAll()
        Log.d("AFTER_DELETE", "${all.size}")
    }
}
