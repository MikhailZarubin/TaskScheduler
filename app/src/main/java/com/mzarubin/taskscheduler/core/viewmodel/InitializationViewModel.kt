package com.mzarubin.taskscheduler.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mzarubin.taskscheduler.datamodel.InitializationState

class InitializationViewModel: ViewModel() {
    private val _initializationStateLiveData: MutableLiveData<InitializationState> = MutableLiveData()
    val initializationLiveData: LiveData<InitializationState> = _initializationStateLiveData

    fun handleOnCreateView() {
        TODO("Not yet implemented")
    }
}