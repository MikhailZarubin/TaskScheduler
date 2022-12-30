package com.mzarubin.taskscheduler.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.core.repository.IInitializationRepository
import com.mzarubin.taskscheduler.datamodel.InitializationState
import com.mzarubin.taskscheduler.datamodel.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitializationViewModel @Inject constructor(
    private val initializationRepository: IInitializationRepository,
    private val accountRepository: IAccountRepository
) : BaseViewModel() {
    private val _initializationStateLiveData: MutableLiveData<InitializationState> =
        MutableLiveData()
    val initializationLiveData: LiveData<InitializationState> = _initializationStateLiveData

    private var initializationState: InitializationState? = null

    fun handleOnCreateView() {
        _loadingStateLiveData.postValue(LoadingState.INACTIVE)

        viewModelScope.launch {
            initializationState = if (initializationRepository.isFirstLaunchApplication()) {
                InitializationState.FIRST_LAUNCH_APPLICATION
            } else {
                if (initializationRepository.isUserAuthorized()) {
                    InitializationState.AUTHORIZATION_IS_NOT_REQUIRED
                } else {
                    InitializationState.AUTHORIZATION_REQUIRED
                }
            }
            if (!loadingShouldDisplayed) {
                loadingJobFinished()
            }
        }
    }

    override fun loadingJobFinished() {
        super.loadingJobFinished()
        initializationState?.let {
            _loadingStateLiveData.postValue(LoadingState.INACTIVE)
            _initializationStateLiveData.postValue(it)
        }
    }
}