package com.example.kino.features.content.domain.repository

import com.example.kino.features.content.data.datasource.ContentLocalDataSource
import com.example.kino.features.content.data.datasource.ContentRemoteDataSource
import com.example.kino.models.Movie
import com.example.kino.models.MovieDetails

class ContentRepositoryImpl(
    private val remote: ContentRemoteDataSource,
    // val local: ContentLocalDataSource
) : ContentRepository {

    override suspend fun getMoviePopular(page: Int, apiKey: String): List<Movie>? {
        return remote.getMoviePopular(page, apiKey)
    }


    override suspend fun getMovieDetail(): MovieDetails {
        TODO("Not yet implemented")
    }
}
