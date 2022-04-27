package com.example.kino.features.content.presentation.home.details

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import coil.load
import com.example.kino.R

import com.example.kino.databinding.FragmentContentDetailBinding
import com.example.kino.features.content.data.models.Movie
import java.util.*

class ContentDetailFragment() : Fragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: FragmentContentDetailBinding

    constructor(content: Movie) : this() {
        arguments = Bundle().apply {
            putParcelable(CONTENT, content)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = arguments?.getParcelable<Movie>(CONTENT)

        binding.schedule.setOnClickListener {
            showScheduleDialog()
        }

        content?.posterUrl.let {
            if (it != null) {
                binding.poster.load(it)
            }
        }

        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            title = "${content?.nameRu}"
        }

        binding.description.apply {
            text = "${content?.nameRu}"
        }
    }

    private fun showScheduleDialog() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        TimePickerDialog(
            activity,
            this,
            hour,
            minute,
            DateFormat.is24HourFormat(activity)
        ).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Log.d("TIME_PICKER", "${hourOfDay} ${minute}")
    }

    companion object {
        private const val CONTENT = "content"
    }
}