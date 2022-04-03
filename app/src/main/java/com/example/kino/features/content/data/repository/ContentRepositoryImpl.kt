package com.example.kino.features.content.data.repository

import android.content.Context
import com.example.kino.features.content.data.datasource.ContentRemoteDataSource
import com.example.kino.features.content.domain.repository.ContentRepository
import com.example.kino.models.Movie
import com.example.kino.models.MovieDetails

class ContentRepositoryImpl(
    private val context: Context,
    private val remote: ContentRemoteDataSource,
) : ContentRepository {

    // override fun getMoviePopular(page: Int, apiKey: String): List<Movie>? {
    //     return remote.getMoviePopular(page, apiKey)
    // }

    private fun fetchMoviePopular(page: Int, apiKey: String) {
        // TODO: here logic get data from remote or local repository
    }

    override suspend fun getMoviePopular(page: Int, apiKey: String): List<Movie>? {
        return remote.getMoviePopular(page, apiKey)
    }
}
