package com.example.kino.features.content.presentation.home.details

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.kino.MainViewModel
import com.example.kino.R

import com.example.kino.databinding.FragmentContentDetailBinding
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.data.models.Schedule
import com.example.kino.utils.hide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ContentDetailFragment() : Fragment(), TimePickerDialog.OnTimeSetListener {
    private val vm: ContentDetailViewModel by sharedViewModel()
    private lateinit var binding: FragmentContentDetailBinding
    private lateinit var movie: Movie

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

    private val scheduleObserver = Observer<Schedule> {
        if (it != null) {
            binding.scheduleText.apply {
                visibility = View.VISIBLE
                text = it.time
            }
        } else {
            binding.scheduleText.hide()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.schedule.observe(viewLifecycleOwner, scheduleObserver)
        arguments?.getParcelable<Movie>(CONTENT).let {
            if (it != null) {
                movie = it
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            vm.getScheduleByIdUseCase(movie.filmId)
        }

        binding.schedule.setOnClickListener {
            showScheduleDialog()
        }

        movie.posterUrl.let {
            binding.poster.load(it)
        }

        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            title = "${movie.nameRu}"
        }

        binding.description.apply {
            // text = "${hasSchedule?.time}"
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
        val schedule =
            Schedule(
                title = movie.nameRu,
                body = movie.nameRu,
                time = "${hourOfDay}:${minute}",
                filmId = movie.filmId
            )
        CoroutineScope(Dispatchers.IO).launch { vm.createSchedule(schedule) }
    }

    companion object {
        private const val CONTENT = "content"
    }
}