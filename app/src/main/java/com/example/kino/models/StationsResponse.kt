package com.example.kino.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class StationsResponse(
    @SerializedName("stations") val stations: List<Station>,
    @SerializedName("meta") val meta: Meta,
) : Parcelable

@Parcelize
class Station(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("author") val author: String,
    @SerializedName("description") val description: String,
    @SerializedName("artists") val artists: String,
    @SerializedName("links") val links: StationLinks?,
) : Parcelable

@Parcelize
class StationLinks(
    @SerializedName("mediumImage") val mediumImage: MediumImage,
    @SerializedName("largeImage") val largeImage: LargeImage,
) : Parcelable

@Parcelize
class MediumImage(
    @SerializedName("href") val href: String,
) : Parcelable

@Parcelize
class LargeImage(
    @SerializedName("href") val href: String,
) : Parcelable
