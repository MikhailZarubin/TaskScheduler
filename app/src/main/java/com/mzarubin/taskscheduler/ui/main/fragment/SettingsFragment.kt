package com.mzarubin.taskscheduler.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mzarubin.taskscheduler.core.viewmodel.BaseViewModel
import com.mzarubin.taskscheduler.core.viewmodel.SettingsViewModel
import com.mzarubin.taskscheduler.databinding.FragmentSettingsBinding
import com.mzarubin.taskscheduler.ui.NavigationFragment

class SettingsFragment: NavigationFragment<SettingsViewModel>() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun initViewModel(): BaseViewModel {
        val viewModel = ViewModelProvider(this, viewModelFactory)[SettingsViewModel::class.java]
        binding.signOutButton.setOnClickListener {
            viewModel.handleClickingOnSignOut()
        }
        return viewModel
    }
}