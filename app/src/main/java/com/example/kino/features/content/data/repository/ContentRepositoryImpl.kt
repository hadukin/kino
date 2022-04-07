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

    private fun localMoviePopular(page: Int) {
        local.getAll()
    }

    override suspend fun getMoviePopular(page: Int): ArrayList<Movie>? {
        val _local = local.getAll()

        Log.d("LLLLL", "${_local.value}")
        return remote.getMoviePopular(page)
    }
}
