package com.example.kino.features.content.domain.repository

import com.example.kino.features.content.data.models.Movie
import com.example.kino.models.MovieDetails

interface ContentRepository {
    suspend fun getMoviePopular(page: Int): ArrayList<Movie>?
    // fun getMovieDetail(): MovieDetails
}