package com.example.kino.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.UUID

class Content(
    val id: UUID,
    val name: String,
    val description: String,
    val poster: Int,
) : Serializable