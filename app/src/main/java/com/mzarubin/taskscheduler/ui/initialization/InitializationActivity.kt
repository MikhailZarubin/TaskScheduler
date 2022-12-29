package com.mzarubin.taskscheduler.ui.initialization

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mzarubin.taskscheduler.core.viewmodel.InitializationViewModel
import com.mzarubin.taskscheduler.databinding.ActivityInitializationBinding
import com.mzarubin.taskscheduler.datamodel.InitializationState

class InitializationActivity: AppCompatActivity() {
    private lateinit var binding: ActivityInitializationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitializationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: InitializationViewModel by viewModels()
        viewModel.initializationLiveData.observe(this) {
            when(it) {
                InitializationState.AUTHORIZATION_REQUIRED -> {}
                InitializationState.AUTHORIZATION_IS_NOT_REQUIRED -> {}
                InitializationState.FIRST_LAUNCH_APPLICATION -> {}
                else -> {}
            }
        }
    }
}