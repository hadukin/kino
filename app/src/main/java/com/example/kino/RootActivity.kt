package com.example.kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kino.databinding.ActivityRootBinding
import com.example.kino.views.favorite.FavoriteFragment
import com.example.kino.views.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        // val navHostFragment =
        //     supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // val navController = navHostFragment.navController
        //
        // findViewById<BottomNavigationView>(R.id.navigate).setupWithNavController(navController)
    }
}