package com.example.kino.features.content.domain.repository

import com.example.kino.models.Movie
import com.example.kino.models.MovieDetails

interface ContentRepository {
    suspend fun getMoviePopular(page: Int, apiKey: String): List<Movie>?
    suspend fun getMovieDetail(): MovieDetails
}