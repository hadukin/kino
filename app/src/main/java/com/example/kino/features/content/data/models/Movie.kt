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
    @PrimaryKey @ColumnInfo(name = "filmId") @SerializedName("filmId") val id: Int,
    @ColumnInfo(name = "nameRu") @SerializedName("nameRu") val nameRu: String,
    // @ColumnInfo(name = "nameEn") @SerializedName("nameEn") val nameEn: String,
    @ColumnInfo(name = "year") @SerializedName("year") val year: String,
    @ColumnInfo(name = "rating") @SerializedName("rating") val rating: String,
    @ColumnInfo(name = "ratingVoteCount") @SerializedName("ratingVoteCount") val ratingVoteCount: Int,
    @ColumnInfo(name = "posterUrl") @SerializedName("posterUrl") val posterUrl: String,
    @ColumnInfo(name = "posterUrlPreview") @SerializedName("posterUrlPreview") val posterUrlPreview: String,

    // Custom field, does not come from api
    @ColumnInfo(name = "is_favorite") @SerializedName("is_favorite") var isFavorite: Boolean = false,
) : Parcelable


// @Entity(tableName = "movies")
// @Parcelize
// data class Movie(
//     @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") val id: Int,
//     @ColumnInfo(name = "adult") @SerializedName("adult") val adult: Boolean,
//     @ColumnInfo(name = "title") @SerializedName("title") val title: String,
//     @ColumnInfo(name = "poster_path") @SerializedName("poster_path") val posterPath: String,
//     @ColumnInfo(name = "backdrop_path") @SerializedName("backdrop_path") val backdropPath: String,
//     @ColumnInfo(name = "release_date") @SerializedName("release_date") val releaseDate: String,
//     @ColumnInfo(name = "vote_average") @SerializedName("vote_average") val voteAverage: Double,
//     @ColumnInfo(name = "vote_count") @SerializedName("vote_count") val voteCount: Int,
//     @ColumnInfo(name = "overview") @SerializedName("overview") val overview: String,
//
//     // Custom field, does not come from api
//     @ColumnInfo(name = "is_favorite") @SerializedName("is_favorite") var isFavorite: Boolean = false,
// ) : Parcelable
//
//
// val Movie.posterUrl: String
//     get() = "https://image.tmdb.org/t/p/original/${this.posterPath}"