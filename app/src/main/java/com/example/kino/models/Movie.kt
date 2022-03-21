package com.example.kino.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

    var isFavorite: Boolean = false,
) : Parcelable

// fun Movie.posterUrl(): String {
//     return "https://image.tmdb.org/t/p/original/${this.posterPath}"
// }

val Movie.posterUrl: String
    get() = "https://image.tmdb.org/t/p/original/${this.posterPath}"