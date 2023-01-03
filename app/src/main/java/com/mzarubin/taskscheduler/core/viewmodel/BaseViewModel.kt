package com.mzarubin.taskscheduler.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.datamodel.ExternalNavigationDataModel
import com.mzarubin.taskscheduler.datamodel.InternalNavigationDataModel
import com.mzarubin.taskscheduler.datamodel.LoadingState
import com.mzarubin.taskscheduler.util.INSTANT_LOAD_TIMEOUT_MS
import com.mzarubin.taskscheduler.util.MINIMUM_LOADING_TIMEOUT_MS
import com.mzarubin.taskscheduler.util.SingleLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("PropertyName")
open class BaseViewModel : ViewModel() {
    protected val _internalNavigationLiveData: SingleLiveData<InternalNavigationDataModel> = SingleLiveData()
    val internalNavigationLiveData: LiveData<InternalNavigationDataModel> = _internalNavigationLiveData

    protected val _externalNavigationLivaData: SingleLiveData<ExternalNavigationDataModel> = SingleLiveData()
    val externalNavigationLiveData: LiveData<ExternalNavigationDataModel> = _externalNavigationLivaData
}