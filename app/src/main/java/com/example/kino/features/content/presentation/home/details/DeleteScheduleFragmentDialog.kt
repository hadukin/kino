package com.example.kino.features.content.presentation.home.details

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.kino.R

class DeleteScheduleFragmentDialog : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Удалить напоминание")
            .setPositiveButton(getString(R.string.button_delete_reminder)) { _, _ ->
                val result = Bundle().apply {
                    putParcelable(
                        ContentDetailFragment.SCHEDULE_RESULT,
                        ScheduleResult.DeleteSchedule(true)
                    )
                }
                parentFragmentManager.setFragmentResult(
                    ContentDetailFragment.SCHEDULE_DIALOG_RESULT,
                    result
                )
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
               // TODO: negative logic
            }
            .create()
}