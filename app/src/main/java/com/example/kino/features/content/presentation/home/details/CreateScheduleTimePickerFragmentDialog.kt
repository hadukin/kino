package com.example.kino.features.content.presentation.home.details

import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.kino.services.ScheduleMovieReceiver
import java.util.*


class CreateScheduleTimePickerFragmentDialog : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {

        val hours = if (hourOfDay < 10) {
            "0${hourOfDay}"
        } else {
            "$hourOfDay"
        }

        val minutes = if (minute < 10) {
            "0${minute}"
        } else {
            minute
        }

        val result = Bundle().apply {
            putParcelable(
                ContentDetailFragment.SCHEDULE_RESULT,
                ScheduleResult.CreateSchedule(
                    time = "${hours}:${minutes}",
                    hourOfDay = hourOfDay,
                    minute = minute
                )
            )
        }

        parentFragmentManager.setFragmentResult(
            ContentDetailFragment.SCHEDULE_DIALOG_RESULT,
            result
        )
    }
}