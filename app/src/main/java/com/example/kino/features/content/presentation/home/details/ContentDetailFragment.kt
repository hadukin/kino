package com.example.kino.features.content.presentation.home.details

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil.load
import com.example.kino.R

import com.example.kino.databinding.FragmentContentDetailBinding
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.data.models.Schedule
import com.example.kino.services.ScheduleMovieReceiver
import com.example.kino.utils.hide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*


class ContentDetailFragment() : Fragment() {
    private val vm: ContentDetailViewModel by sharedViewModel()
    private lateinit var binding: FragmentContentDetailBinding
    private lateinit var movie: Movie
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

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

    private val scheduleObserver = Observer<Schedule?> {
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

        parentFragmentManager.setFragmentResultListener(
            SCHEDULE_DIALOG_RESULT,
            this
        ) { _, result ->

            val r = result.getParcelable<ScheduleResult>(SCHEDULE_RESULT)

            when (r) {
                is ScheduleResult.CreateSchedule -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        vm.createSchedule(
                            Schedule(
                                title = movie.nameRu,
                                body = movie.nameRu,
                                time = r.time,
                                filmId = movie.filmId
                            )
                        )
                    }
                    setScheduleAlarm(hours = r.hourOfDay, minute = r.minute)
                }
                is ScheduleResult.DeleteSchedule -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        vm.schedule.value?.let {
                            vm.deleteSchedule(it)
                        }
                    }
                }
                else -> {}
            }
        }

        binding.schedule.setOnClickListener {
            if (vm.schedule.value != null) {
                DeleteScheduleFragmentDialog().show(
                    parentFragmentManager,
                    SCHEDULE_DIALOG_RESULT
                )
            } else {
                CreateScheduleTimePickerFragmentDialog().show(
                    parentFragmentManager,
                    SCHEDULE_DIALOG_RESULT
                )
            }
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

    private fun setScheduleAlarm(hours: Int, minute: Int) {
        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), ScheduleMovieReceiver::class.java)
        intent.putExtra("movie_name", "${movie.nameRu}")
        pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)

        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = hours
        calendar[Calendar.MINUTE] = minute
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent
        )
    }

    companion object {
        private const val CONTENT = "content"
        const val SCHEDULE_DIALOG_RESULT = "SCHEDULE_DIALOG_RESULT"
        const val SCHEDULE_RESULT = "SCHEDULE_RESULT"
    }
}


