package com.example.kino.features.content.domain.repository

import com.bumptech.glide.load.engine.Resource
import com.example.kino.features.content.data.models.Movie

interface ContentRepository {
    suspend fun getMovieById(id: Int): Movie
    suspend fun getMovies(page: Int): List<Movie>
    suspend fun saveAllMovies(items: List<Movie>)
    suspend fun saveToFavorite(item: Movie)
    suspend fun deleteFromFavorite(item: Movie)
}