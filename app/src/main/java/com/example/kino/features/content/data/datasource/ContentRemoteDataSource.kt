package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository

interface ContentRemoteDataSource {
    suspend fun getMovies(page: Int): List<Movie>

    suspend fun saveToFavorite(item: Movie)

    suspend fun deleteFromFavorite(item: Movie)
}