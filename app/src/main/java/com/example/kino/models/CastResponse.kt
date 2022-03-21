package com.example.kino.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<Cast>,
) : Parcelable