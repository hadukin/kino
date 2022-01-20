package com.example.kino.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailResult(var isFavorite: Boolean = false, var message: String?) : Parcelable