package com.mzarubin.taskscheduler.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.core.repository.IInitializationRepository
import com.mzarubin.taskscheduler.datamodel.InitializationState
import com.mzarubin.taskscheduler.datamodel.InternalNavigationDataModel
import com.mzarubin.taskscheduler.datamodel.LoadingState
import com.mzarubin.taskscheduler.ui.initialization.fragment.InitializationFragmentDirections
import com.mzarubin.taskscheduler.util.pendingDisplayLoading
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class InitializationViewModel @Inject constructor(
    private val initializationRepository: IInitializationRepository
) : BaseViewModel() {
    private val _loadingStateLiveData: MutableLiveData<LoadingState> = MutableLiveData()
    val loadingStateLiveData: LiveData<LoadingState> = _loadingStateLiveData

    private var initializationState: InitializationState? = null
    private var loadingShouldDisplayed = false
    private lateinit var loadingJob: Job

    fun handleOnViewCreated() {
        _loadingStateLiveData.postValue(LoadingState.INACTIVE)
        loadingJob = pendingDisplayLoading(viewModelScope) {
            loadingShouldDisplayed = it
            onStateChanged()
        }

        viewModelScope.launch {
            initializationState = if (initializationRepository.isFirstLaunchApplication()) {
                initializationRepository.clearFirstLaunchState()
                InitializationState.FIRST_LAUNCH_APPLICATION
            } else {
                if (initializationRepository.isUserAuthorized()) {
                    InitializationState.AUTHORIZATION_IS_NOT_REQUIRED
                } else {
                    InitializationState.AUTHORIZATION_REQUIRED
                }
            }
            onStateChanged()
        }
    }

    private fun onStateChanged() {
        if (loadingShouldDisplayed) {
            _loadingStateLiveData.postValue(LoadingState.ACTIVE)
        } else {
            if (!loadingJob.isCancelled) {
                loadingJob.cancel()
            }
            initializationState?.let {
                _loadingStateLiveData.postValue(LoadingState.INACTIVE)
                when (it) {
                    InitializationState.FIRST_LAUNCH_APPLICATION -> {
                        _internalNavigationLiveData.postValue(
                            InternalNavigationDataModel(
                                InitializationFragmentDirections.actionInitializationFragmentToAuthorizationFragment(
                                    true
                                )
                            )
                        )
                    }
                    InitializationState.AUTHORIZATION_REQUIRED -> {
                        _internalNavigationLiveData.postValue(
                            InternalNavigationDataModel(
                                InitializationFragmentDirections.actionInitializationFragmentToAuthorizationFragment()
                            )
                        )
                    }
                    InitializationState.AUTHORIZATION_IS_NOT_REQUIRED -> {
                        //TODO: open Main Activity
                    }
                }
            }
        }
    }
}