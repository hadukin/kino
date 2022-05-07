package com.example.kino.features.content.presentation.home.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

sealed class ScheduleResult : Parcelable {
    @Parcelize
    class CreateSchedule(
        val time: String,
        val hourOfDay: Int,
        val minute: Int,
    ) : ScheduleResult(), Parcelable

    @Parcelize
    class DeleteSchedule(val isDelete: Boolean) : ScheduleResult(), Parcelable
}