package com.example.kino.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Meta(
    @SerializedName("returnedCount") val returnedCount: Int?,
    @SerializedName("totalCount") val totalCount: Int?,
) : Parcelable