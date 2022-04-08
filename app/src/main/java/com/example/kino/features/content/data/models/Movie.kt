package com.example.kino.features.content.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class Movie(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") val id: Int,
    @ColumnInfo(name = "adult") @SerializedName("adult") val adult: Boolean,
    @ColumnInfo(name = "title") @SerializedName("title") val title: String,
    @ColumnInfo(name = "poster_path") @SerializedName("poster_path") val posterPath: String,
    @ColumnInfo(name = "backdrop_path") @SerializedName("backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "release_date") @SerializedName("release_date") val releaseDate: String,
    @ColumnInfo(name = "vote_average") @SerializedName("vote_average") val voteAverage: Double,
    @ColumnInfo(name = "vote_count") @SerializedName("vote_count") val voteCount: Int,
    @ColumnInfo(name = "overview") @SerializedName("overview") val overview: String,

    // Custom field
    @ColumnInfo(name = "is_favorite") @SerializedName("is_favorite") var isFavorite: Boolean = false,
) : Parcelable


val Movie.posterUrl: String
    get() = "https://image.tmdb.org/t/p/original/${this.posterPath}"