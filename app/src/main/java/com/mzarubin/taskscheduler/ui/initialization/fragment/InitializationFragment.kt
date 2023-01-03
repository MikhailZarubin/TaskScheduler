package com.mzarubin.taskscheduler.ui.initialization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mzarubin.taskscheduler.core.viewmodel.InitializationViewModel
import com.mzarubin.taskscheduler.core.viewmodel.BaseViewModel
import com.mzarubin.taskscheduler.databinding.FragmentInitializationBinding
import com.mzarubin.taskscheduler.datamodel.LoadingState
import com.mzarubin.taskscheduler.ui.NavigationFragment

class InitializationFragment : NavigationFragment<InitializationViewModel>() {
    private var _binding: FragmentInitializationBinding? = null
    private val binding: FragmentInitializationBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentInitializationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initViewModel(): BaseViewModel {
        val viewModel = ViewModelProvider(this, viewModelFactory)[InitializationViewModel::class.java]
        viewModel.handleOnViewCreated()

        viewModel.loadingStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                LoadingState.ACTIVE -> binding.loadingProgressBar.visibility = View.VISIBLE
                LoadingState.INACTIVE -> binding.loadingProgressBar.visibility = View.GONE
                else -> {}
            }
        }
        return viewModel
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}