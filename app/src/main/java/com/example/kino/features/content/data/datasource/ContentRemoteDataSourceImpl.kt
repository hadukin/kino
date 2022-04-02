package com.example.kino.features.content.data.datasource

import com.example.kino.api.MovieClient
import com.example.kino.models.Movie
import com.example.kino.models.MovieDetails
import com.example.kino.models.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentRemoteDataSourceImpl(private val api: MovieClient) : ContentRemoteDataSource {
    override suspend fun getMoviePopular(page: Int, apiKey: String): List<Movie> {
        val list = mutableListOf<Movie>()
        api.getMoviePopular(page, apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body().let {
                    if (it != null) {
                        list.addAll(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable) {

            }
        })
        return list
    }

    override suspend fun getMovieDetail(): MovieDetails {
        TODO("Not yet implemented")
    }
}