package com.example.kino.features.content.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesResponse(
    @SerializedName("films") val results: ArrayList<Movie>,
    @SerializedName("pagesCount") val total_pages: Int,
) : Parcelable
