package com.example.kino.features.content.domain.repository

import com.example.kino.features.content.data.models.Movie

interface ContentRepository {
    suspend fun getMoviePopular(page: Int): ArrayList<Movie>?
    suspend fun saveToFavorite(item: Movie)
    suspend fun deleteFromFavorite(item: Movie)
}