package com.mzarubin.taskscheduler.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mzarubin.taskscheduler.R
import com.mzarubin.taskscheduler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.displayFragment.id) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.profile_fragment,
                R.id.to_do_list_fragment,
                R.id.settings_fragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.tabBar.setupWithNavController(navController)
        binding.toolBar.setupWithNavController(navController, appBarConfiguration)
    }
}