package com.mzarubin.taskscheduler.ui.initialization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mzarubin.taskscheduler.databinding.ActivityInitializationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitializationActivity: AppCompatActivity() {
    private lateinit var binding: ActivityInitializationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitializationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}