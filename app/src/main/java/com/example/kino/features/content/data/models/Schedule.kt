package com.example.kino.features.content.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "schedule")
@Parcelize
data class Schedule(
    @PrimaryKey @ColumnInfo(name = "filmId") @SerializedName("filmId") val filmId: Int,
    // @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") @SerializedName("id") val id: Int? = null,
    @ColumnInfo(name = "title") @SerializedName("title") val title: String,
    @ColumnInfo(name = "body") @SerializedName("body") val body: String,
    @ColumnInfo(name = "time") @SerializedName("time") val time: String,
) : Parcelable
