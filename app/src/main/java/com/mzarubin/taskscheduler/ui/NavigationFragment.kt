package com.mzarubin.taskscheduler.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mzarubin.taskscheduler.core.viewmodel.BaseViewModel
import com.mzarubin.taskscheduler.di.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class NavigationFragment<VM: BaseViewModel>: DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = initViewModel()
        viewModel.internalNavigationLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(it.navDirection, it.navOptions)
        }

        viewModel.externalNavigationLiveData.observe(viewLifecycleOwner) {
            Intent(context, it.cls.java).apply {
                it.flags?.let {flags -> this.flags = flags }
                it.action?.let {action -> this.action = action }
                startActivity(this)
            }
        }
    }

    abstract fun initViewModel(): BaseViewModel
}