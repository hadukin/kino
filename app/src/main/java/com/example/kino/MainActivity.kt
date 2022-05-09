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
import androidx.navigation.navArgument
import androidx.navigation.ui.setupWithNavController
import com.example.kino.databinding.ActivityMainBinding
import com.example.kino.features.content.data.models.Movie
import com.example.kino.services.ScheduleMovieReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import java.util.*


class MainActivity : AppCompatActivity() {
    // private val viewModel: MainViewModel by viewModels()
    // val viewModel: MainViewModel by viewModel()
    // private val viewModel by viewModel<MainViewModel>()

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var alarmManager: AlarmManager

    @SuppressLint("ResourceType", "LongLogTag")
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

        val hasContent = bundle?.getParcelable<Movie>("content")
        Log.d("$TAG/hasContent", "${hasContent}")

        if (hasContent != null) {
            // navController.createDeepLink().addDestination(R.layout.fragment_content_detail, bundle)
            // navController.navigate(R.layout.fragment_content_detail, bundle, null)
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

    @SuppressLint("LongLogTag")
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val hasContent = intent?.extras?.get("content")

        Log.d("${TAG}/onNewIntentMain", "${hasContent}")
    }

    @SuppressLint("LongLogTag")
    private fun setNotificationBackgroundAlarmManager() {
        Log.d("$TAG/BACKGROUND_ALARM", "BACKGROUND_ALARM_MANAGER")
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