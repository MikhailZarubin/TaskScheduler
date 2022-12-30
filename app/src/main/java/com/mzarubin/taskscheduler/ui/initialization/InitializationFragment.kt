package com.mzarubin.taskscheduler.ui.initialization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mzarubin.taskscheduler.core.viewmodel.InitializationViewModel
import com.mzarubin.taskscheduler.databinding.FragmentInitializationBinding
import com.mzarubin.taskscheduler.datamodel.InitializationState
import com.mzarubin.taskscheduler.datamodel.LoadingState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitializationFragment: Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: InitializationViewModel by viewModels()
        viewModel.handleOnCreateView()

        viewModel.loadingStateLiveData.observe(viewLifecycleOwner) {
            when(it) {
                LoadingState.ACTIVE -> binding.loadingProgressBar.visibility = View.VISIBLE
                LoadingState.INACTIVE -> binding.loadingProgressBar.visibility = View.GONE
                else -> {}
            }
        }

        viewModel.initializationLiveData.observe(viewLifecycleOwner) {
            when(it) {
                InitializationState.AUTHORIZATION_REQUIRED -> {}
                InitializationState.AUTHORIZATION_IS_NOT_REQUIRED -> {}
                InitializationState.FIRST_LAUNCH_APPLICATION -> {}
                else -> {}
            }
        }
    }
}