package com.mzarubin.taskscheduler.core.viewmodel

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.datamodel.ExternalNavigationDataModel
import com.mzarubin.taskscheduler.ui.initialization.InitializationActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val accountRepository: IAccountRepository
) : BaseViewModel() {
    fun handleClickingOnSignOut() {
        viewModelScope.launch {
            accountRepository.signOut()
            _externalNavigationLivaData.postValue(
                ExternalNavigationDataModel(
                    InitializationActivity::class,
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
            )
        }
    }
}