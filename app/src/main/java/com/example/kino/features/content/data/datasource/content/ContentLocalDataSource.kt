package com.example.kino.features.content.data.datasource.content

import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository

interface ContentLocalDataSource {
    suspend fun getMovies(): List<Movie>

    suspend fun getMovieById(id: Int): Movie

    suspend fun saveAllMovies(items: List<Movie>)

    suspend fun saveToFavorite(item: Movie)

    suspend fun deleteFromFavorite(item: Movie)
}