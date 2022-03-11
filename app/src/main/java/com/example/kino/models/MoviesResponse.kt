package com.example.kino.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MoviesResponse(
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("total_pages") val total_pages: Int,
) : Parcelable

@Parcelize
data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("overview") val overview: String,
) : Parcelable

fun Movie.posterUrl(): String {
    return "https://image.tmdb.org/t/p/original/${this.posterPath}"
}
