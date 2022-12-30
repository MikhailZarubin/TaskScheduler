package com.mzarubin.taskscheduler.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.datamodel.LoadingState
import com.mzarubin.taskscheduler.util.INSTANT_LOAD_TIMEOUT_MS
import com.mzarubin.taskscheduler.util.MINIMUM_LOADING_TIMEOUT_MS
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected val _loadingStateLiveData: MutableLiveData<LoadingState> = MutableLiveData()
    val loadingStateLiveData: LiveData<LoadingState> = _loadingStateLiveData
    protected var loadingShouldDisplayed = false

    private var pendingDisplayLoading: Job =
        viewModelScope.launch {
            delay(INSTANT_LOAD_TIMEOUT_MS)
            loadingShouldDisplayed = true
            _loadingStateLiveData.postValue(LoadingState.ACTIVE)
            delay(MINIMUM_LOADING_TIMEOUT_MS)
            loadingShouldDisplayed = false
            loadingJobFinished()
        }

    protected open fun loadingJobFinished() {
        if (!pendingDisplayLoading.isCancelled) {
            pendingDisplayLoading.cancel()
        }
    }
}