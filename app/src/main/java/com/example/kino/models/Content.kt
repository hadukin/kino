package com.example.kino.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Content(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("poster") val poster: String,
    @SerializedName("is_favorite") var isFavorite: Boolean = false
) : Parcelable