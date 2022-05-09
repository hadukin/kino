package com.example.kino

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgument
import androidx.navigation.ui.setupWithNavController
import com.example.kino.databinding.ActivityMainBinding
import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.presentation.home.HomeFragment
import com.example.kino.features.content.presentation.home.HomeFragmentDirections
import com.example.kino.features.content.presentation.home.details.ContentDetailFragment
import com.example.kino.features.content.presentation.home.details.ContentDetailFragmentArgs
import com.example.kino.services.ScheduleMovieReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import java.util.*


class MainActivity : AppCompatActivity() {
    // private val viewModel: MainViewModel by viewModels()
    // val viewModel: MainViewModel by viewModel()
    // private val viewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupKoinFragmentFactory()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.graph.addInDefaultArgs(bundle)
        navController.setGraph(navController.graph, bundle)
        findViewById<BottomNavigationView>(R.id.navigate).setupWithNavController(navController)

        val content = bundle?.getParcelable<Movie>("content")
        if (content != null) {

            val action = HomeFragmentDirections.actionNavigationHomeToContentDetailFragment(content)
            navController.navigate(action)

            // bundle.putParcelable("content", content)
            // navController.navigate(
            //     R.id.action_navigation_home_to_contentDetailFragment,
            //     bundle,
            // )
        }
    }

    override fun onDestroy() {
        setNotificationBackgroundAlarmManager()
        super.onDestroy()
    }

    override fun onStop() {
        setNotificationBackgroundAlarmManager()
        super.onStop()
    }

    private fun setNotificationBackgroundAlarmManager() {
        val myIntent = Intent(applicationContext, ScheduleMovieReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, myIntent, 0)

        alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 60)
        val frequency: Long = 60 * 1000
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            frequency,
            pendingIntent
        )
    }
}