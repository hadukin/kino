package com.example.kino.features.content.domain.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.example.kino.features.content.data.datasource.ContentLocalDataSource
import com.example.kino.features.content.data.datasource.ContentRemoteDataSource
import com.example.kino.models.Movie
import com.example.kino.models.MovieDetails

class ContentRepositoryImpl(
    val remote: ContentRemoteDataSource,
    val local: ContentLocalDataSource
) : ContentRepository {

    override suspend fun getMoviePopular(page: Int, apiKey: String): List<Movie>? {
        TODO("Not yet implemented")
        // remote.getMoviePopular(page, api_key)
    }


    override suspend fun getMovieDetail(): MovieDetails {
        TODO("Not yet implemented")
    }
}
