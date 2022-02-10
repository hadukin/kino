package com.example.kino.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.UUID

@Parcelize
data class Content(
    val id: Int,
    val name: String,
    val description: String,
    val poster: Int,
    var isFavorite: Boolean = false
) : Parcelable