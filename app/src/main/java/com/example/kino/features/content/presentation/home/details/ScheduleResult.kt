package com.example.kino.features.content.presentation.home.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ScheduleResult : Parcelable {
    @Parcelize
    class CreateSchedule(val time: String) : ScheduleResult(), Parcelable

    @Parcelize
    class DeleteSchedule(val isDelete: Boolean) : ScheduleResult(), Parcelable
}