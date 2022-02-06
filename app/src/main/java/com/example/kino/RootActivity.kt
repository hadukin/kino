package com.example.kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.kino.databinding.ActivityRootBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class RootActivity : AppCompatActivity(),
    NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment())

        val navigate: BottomNavigationView = binding.navigate
        navigate.setOnItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> loadFragment(HomeFragment())
            R.id.navigation_favorite -> loadFragment(FavoriteFragment())
            else -> return false
        }
        return true
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }
}