package com.mzarubin.taskscheduler.ui.initialization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mzarubin.taskscheduler.core.viewmodel.InitializationViewModel
import com.mzarubin.taskscheduler.databinding.FragmentInitializationBinding
import com.mzarubin.taskscheduler.datamodel.InitializationState
import com.mzarubin.taskscheduler.datamodel.LoadingState
import com.mzarubin.taskscheduler.di.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class InitializationFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[InitializationViewModel::class.java]
        viewModel.handleOnCreateView()

        viewModel.loadingStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                LoadingState.ACTIVE -> binding.loadingProgressBar.visibility = View.VISIBLE
                LoadingState.INACTIVE -> binding.loadingProgressBar.visibility = View.GONE
                else -> {}
            }
        }

        viewModel.initializationLiveData.observe(viewLifecycleOwner) {
            when (it) {
                InitializationState.AUTHORIZATION_IS_NOT_REQUIRED -> {
                    // TODO: open profile activity
                }
                InitializationState.AUTHORIZATION_REQUIRED -> {
                    // TODO: open authorization activity
                }
                InitializationState.FIRST_LAUNCH_APPLICATION -> {
                    // TODO: open authorization activity with first launch flag
                }
                else -> {}
            }
        }
    }
}